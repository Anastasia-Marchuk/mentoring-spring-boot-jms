package com.amarchuk.jms.extra.service;
import com.amarchuk.jms.extra.model.Order;
import com.amarchuk.jms.extra.model.Type;
import com.amarchuk.jms.extra.model.User;
import com.amarchuk.jms.extra.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Sender {

    @Autowired
    JmsTemplate jmsTemplate;
    Scanner in = new Scanner(System.in);

    private static final String ORDER_QUEUE = "order.confirmed.task3";
    private static final String ORDER_QUEUE_REJECTED = "order.rejected.task3";

    public void makeOrder( ) {

        System.out.println("Input your data for continue making the order.\n");
        System.out.print("Input name: \n");
        String name = in.nextLine();
        System.out.print("Input lastname: \n");
        String lastname = in.nextLine();
        User user = new User(name, lastname);

        System.out.print("Liquids or countable item? Choose a number: \n1.liquids\n2.countable\n");
        int type = in.nextInt();
        int value;
        Item item = null;
        Order order = null;
        if (type == 1) {
            order = orderLiquids(user, in);
        }
        if (type == 2) {
            order = orderItems(user, in);
        }


        boolean isOk=checkOrder(order);
        Sender sender=new Sender();
        if(!isOk){
            sender.send(ORDER_QUEUE_REJECTED, order, false, jmsTemplate);
        } else{
            sender.send(ORDER_QUEUE, order, true, jmsTemplate);
        }

    }

    public void send(String destination, Order message,
                     boolean isConfirmed, JmsTemplate jmsTemplate)  {

        if (isConfirmed) {
            jmsTemplate.convertAndSend(destination, message,
                    messagePostProcessor -> {
                        messagePostProcessor.setStringProperty("selector",
                                "confirmed");
                        return messagePostProcessor;
                    });
        } else {
            jmsTemplate.convertAndSend(destination, message,
                    messagePostProcessor -> {
                        messagePostProcessor.setStringProperty("selector",
                                "failed");
                        return messagePostProcessor;
                    });
        }
    }

    public static Order orderLiquids(User user, Scanner in )   {
        System.out.print("What value do you want? Write value in ml:");
        int value = in.nextInt();
        double price = value * 3;
        Item item = new Item("item", value, Type.LIQUID_ML, price);
        Order order = new Order(1, user, item);
        return order;
    }

    public static Order orderItems(User user, Scanner in) {
        System.out.print("What number of items do you want?\n");
        int value = in.nextInt();
        double price = value * 5.5;
        Item item = new Item("item", value, Type.COUNTABLE_NUMBER, price);
        Order order = new Order(1, user, item);
        return order;

    }

    public static boolean checkOrder(Order order) {
        if (order.getItem().getVolume() > 800 && order.getItem().getType() == Type.LIQUID_ML ||
                order.getItem().getVolume() > 10 && order.getItem().getType() == Type.COUNTABLE_NUMBER) {
            System.out.println("Sorry. You can't make the order on more then 3000 ml(or 10 items) without registration");
            return false;
        } else {
           return true;
        }
    }
}
