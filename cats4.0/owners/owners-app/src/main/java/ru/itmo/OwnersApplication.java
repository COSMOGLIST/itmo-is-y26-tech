package ru.itmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.itmo.rabbit.RabbitConfig;

@SpringBootApplication
@Import(RabbitConfig.class)
public class OwnersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OwnersApplication.class, args);
    }
}
