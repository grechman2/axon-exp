package org.grechman.axonexp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@SpringBootApplication
public class AxonExpApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonExpApplication.class, args);
    }

}
