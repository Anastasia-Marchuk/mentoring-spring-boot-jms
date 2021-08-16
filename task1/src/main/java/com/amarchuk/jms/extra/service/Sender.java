package com.amarchuk.jms.extra.service;

import com.amarchuk.jms.extra.model.Item;
import com.amarchuk.jms.extra.model.Order;
import com.amarchuk.jms.extra.model.Type;
import com.amarchuk.jms.extra.model.User;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Scanner;



@Service
public class Sender {

    private static final String ORDER_QUEUE = "order.queue.task1";


    public void makeOrder(JmsTemplate jmsTemplate ) {

        System.out.println("Input your data for continue making the order.\n");
        Scanner in = new Scanner(System.in);
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
            order = orderLiquids(user, in, jmsTemplate);

        }
        if (type == 2) {
            order = orderItems(user, in, jmsTemplate);

        }
        in.close();

            jmsTemplate.convertAndSend(ORDER_QUEUE, order);
        System.out.println("Order submitted "+order);

    }

    public static Order orderLiquids(User user, Scanner in, JmsTemplate jmsTemplate) {
        System.out.print("What value do you want? Write value in ml:");
        int value = in.nextInt();
        double price = value * 3;
        Item item = new Item("item", value, Type.LIQUID_ML, price);
        Order order = new Order(1, user, item);
        return order;
    }

    public static Order orderItems(User user, Scanner in, JmsTemplate jmsTemplate) {
        System.out.print("What number of items do you want?\n");
        int value = in.nextInt();
        double price = value * 5.5;
        Item item = new Item("item", value, Type.COUNTABLE_NUMBER, price);
        Order order = new Order(1, user, item);
        return order;

    }

}
