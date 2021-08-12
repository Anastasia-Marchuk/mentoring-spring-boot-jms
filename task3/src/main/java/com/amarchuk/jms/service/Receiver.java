package com.amarchuk.jms.service;

import com.amarchuk.jms.model.Order;
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

    private CountDownLatch latch = new CountDownLatch(2);

    public CountDownLatch getLatch() {
        return latch;
    }

    @JmsListener(destination = "orderConfirmed",
            selector = "selector = 'confirmed'")
    public void receiveHigh(Order order) {
        LOGGER.info("received confirmed order='{}'", order);
        writeOrderToFile("confirmed_order",order);
        latch.countDown();
    }

    @JmsListener(destination = "orderRejected",
            selector = "selector = 'failed'")
    public void receiveLow(Order order) {
        LOGGER.info("rejected order='{}'", order);
        writeOrderToFile("rejected_order",order);
        latch.countDown();
    }

    private void writeOrderToFile(String fileName, Order order) {
        File file = new File("/home/anastasia/EpamMentoringProgram/examples/stacy/mentoring-spring-boot-jms/task3/src/main/resources/" + fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()))) {
            writer.write(format("%s%n", order));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}