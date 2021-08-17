package com.amarchuk.jms.extra;

import com.amarchuk.jms.extra.config.ConfigJMS;
import com.amarchuk.jms.extra.service.Sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(ConfigJMS.class)
public class Application2 {

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext context = SpringApplication.run(Application2.class, args);
        Sender sender = context.getBean(Sender.class);
        while(true) {
            sender.makeOrder();
        }

    }
}
