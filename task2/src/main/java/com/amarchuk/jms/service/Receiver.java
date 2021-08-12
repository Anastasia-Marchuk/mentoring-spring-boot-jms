package com.amarchuk.jms.service;
import com.amarchuk.jms.model.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {


    @JmsListener(destination = "orderConfirmed", containerFactory = "myFactory")
    public void receiveMessage(Order order ) {
        System.out.println("Confirmed order<" + order + ">");
    }

    @JmsListener(destination = "orderRejected", containerFactory = "myFactory")
    public void receiveRejectedMessage(Order order) {
        System.out.println("Rejected order <" + order + ">");
    }


}