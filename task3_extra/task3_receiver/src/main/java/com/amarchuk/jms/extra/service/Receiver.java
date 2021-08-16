package com.amarchuk.jms.extra.service;

import com.amarchuk.jms.extra.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

import static java.lang.String.format;


@Component
public class Receiver {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Receiver.class);

    private static final String TOPIC = "topic.test";

    @JmsListener(destination = TOPIC, containerFactory = "myFactory")
    public void receiveHigh(Order order)  {
        LOGGER.info("received confirmed order='{}'", order);
    }


}