package com.amarchuk.jms.extra.service;
import com.amarchuk.jms.extra.model.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private static final String ORDER_QUEUE = "order.confirmed.task2";
    private static final String ORDER_QUEUE_REJECTED = "order.rejected.task2";

    @JmsListener(destination = ORDER_QUEUE, containerFactory = "myFactory")
    public void receiveMessage(Order order ) {

        System.out.println("Confirmed order<" + order + ">");
    }

    @JmsListener(destination = ORDER_QUEUE_REJECTED, containerFactory = "myFactory")
    public void receiveRejectedMessage(Order order) {
        System.out.println("Rejected order <" + order + ">");
    }


}