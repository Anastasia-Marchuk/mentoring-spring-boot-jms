package com.amarchuk.jms.extra.service;

import com.amarchuk.jms.extra.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

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

    private static final String ORDER_QUEUE = "order.confirmed.task3";
    private static final String ORDER_QUEUE_REJECTED = "order.rejected.task3";

    @JmsListener(destination = ORDER_QUEUE,
            selector = "selector = 'confirmed'")
    public void receiveHigh(Order order) {
        LOGGER.info("received confirmed order='{}'", order);
//        writeOrderToFile("confirmed_order",order);
        latch.countDown();
    }

    @JmsListener(destination = ORDER_QUEUE_REJECTED,
            selector = "selector = 'failed'")
    public void receiveLow(Order order) {
        LOGGER.info("rejected order='{}'", order);
//        writeOrderToFile("rejected_order",order);
        latch.countDown();
    }

//    private void writeOrderToFile(String fileName, Order order) {
//        File file = new File("/home/anastasia/EpamMentoringProgram/mentoring-spring-boot-jms/task3/src/main/resources/" + fileName);
//        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()))) {
//            writer.write(format("%s%n", order));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

}