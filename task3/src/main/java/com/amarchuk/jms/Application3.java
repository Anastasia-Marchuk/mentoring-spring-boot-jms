package com.amarchuk.jms;

import com.amarchuk.jms.config.ConfigJMS;
import com.amarchuk.jms.service.Sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(ConfigJMS.class)
public class Application3 {



    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application3.class, args);
        Sender sender = context.getBean(Sender.class);
        sender.makeOrder();
    }
}
