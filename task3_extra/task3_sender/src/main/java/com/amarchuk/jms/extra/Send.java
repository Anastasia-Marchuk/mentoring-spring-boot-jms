package com.amarchuk.jms.extra;


import com.amarchuk.jms.extra.config.ConfigJMS;
import com.amarchuk.jms.extra.service.Sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(ConfigJMS.class)
public class Send {



    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Send.class, args);
        Sender sender = context.getBean(Sender.class);
        while (true) {
            sender.makeOrder();
        }
    }
}
