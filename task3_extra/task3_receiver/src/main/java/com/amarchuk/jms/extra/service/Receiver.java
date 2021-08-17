package com.amarchuk.jms.extra.service;

import com.amarchuk.jms.extra.model.Order;
import com.amarchuk.jms.extra.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;



@Component
public class Receiver {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Receiver.class);

    private static final String TOPIC = "topic.test";

    @JmsListener(destination = TOPIC, containerFactory = "myFactory")
    public void receive(Order order)  {
        LOGGER.info("received confirmed order='{}'", order);
        System.out.println("received confirmed order="+order);
    }

}