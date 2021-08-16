package com.amarchuk.jms.extra;


import com.amarchuk.jms.extra.config.ConfigJMS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(ConfigJMS.class)
public class Receive {



    public static void main(String[] args)  {

       SpringApplication.run(Receive.class, args);

    }
}
