package com.amarchuk.jms.extra;

import com.amarchuk.jms.extra.config.ConfigJMS;
import com.amarchuk.jms.extra.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;


@SpringBootApplication
@Import(ConfigJMS.class)
public class Application2 {

    @Autowired

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application2.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        Sender service=new Sender();
        service.makeOrder(jmsTemplate);




    }
}
