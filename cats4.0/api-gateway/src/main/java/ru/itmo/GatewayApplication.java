package ru.itmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.itmo.rabbit.RabbitConfig;

@SpringBootApplication
@Import({CatClientConfiguration.class, OwnerClientWeb.class, RabbitConfig.class})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}