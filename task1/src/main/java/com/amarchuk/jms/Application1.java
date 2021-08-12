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
public class Application1 {



    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application1.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        Sender service=new Sender();
        service.makeOrder(jmsTemplate);

    }
}