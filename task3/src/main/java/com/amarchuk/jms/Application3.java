package com.amarchuk.jms;

import com.amarchuk.jms.config.ConfigJMS;
import com.amarchuk.jms.service.Sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;


@SpringBootApplication
@Import(ConfigJMS.class)
public class Application3 {

    private static Sender sender=new Sender();


    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application3.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        sender.makeOrder(jmsTemplate);
    }
}
