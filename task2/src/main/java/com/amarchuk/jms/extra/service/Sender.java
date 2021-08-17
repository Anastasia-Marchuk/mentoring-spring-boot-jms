package com.amarchuk.jms.extra.service;

import com.amarchuk.jms.extra.model.Item;
import com.amarchuk.jms.extra.model.Order;
import com.amarchuk.jms.extra.model.Type;
import com.amarchuk.jms.extra.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Sender {

    @Autowired
    JmsTemplate jmsTemplate;
    Scanner in = new Scanner(System.in);

    private static final String ORDER_QUEUE = "order.confirmed.task2";
    private static final String ORDER_QUEUE_REJECTED = "order.rejected.task2";

    public void makeOrder() throws InterruptedException {

        Thread.sleep(2500);
        System.out.println("Input your data for continue making the order.\n");
        System.out.print("Input name: \n");
        String name = in.nextLine();
        Thread.sleep(1500);
        System.out.print("Input lastname: \n");
        String lastname = in.nextLine();
        User user = new User(name, lastname);

        System.out.print("Liquids or countable item? Choose a number: \n1.liquids\n2.countable\n");
        int type = in.nextInt();

        Order order = null;
        if (type == 1) {
            order = orderLiquids(user, in);
        }
        if (type == 2) {
            order = orderItems(user, in);
        }

        if (order.getItem().getVolume() > 3000 && order.getItem().getType() == Type.LIQUID_ML ||
                order.getItem().getVolume() > 10 && order.getItem().getType() == Type.COUNTABLE_NUMBER) {
            System.out.println("Sorry. You can't make the order on more then 3000 ml(or 10 items) without registration");
            jmsTemplate.convertAndSend(ORDER_QUEUE_REJECTED, order);
        } else {
            jmsTemplate.convertAndSend(ORDER_QUEUE, order);

        }

    }

    public static Order orderLiquids(User user, Scanner in) {
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
}
