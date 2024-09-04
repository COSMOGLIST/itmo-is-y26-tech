package ru.itmo.gateway.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.itmo.OwnerClient;
import ru.itmo.messages.IdMessage;
import ru.itmo.models.OwnerDto;
import ru.itmo.rabbit.QueueCommands;

import java.util.List;

@Service
public class OwnerService {
    private final RabbitTemplate rabbitTemplate;
    private final OwnerClient ownerClient;


    public OwnerService(RabbitTemplate rabbitTemplate, OwnerClient ownerClient) {
        this.rabbitTemplate = rabbitTemplate;
        this.ownerClient = ownerClient;
    }

    public OwnerDto findById(int id) {
        return ownerClient.findById(id);
    }

    public void creation(OwnerDto ownerDto) {
        rabbitTemplate.convertAndSend(QueueCommands.NEW_OWNER, ownerDto);
    }

    public void deleteById(int id) {
        rabbitTemplate.convertAndSend(QueueCommands.DELETE_OWNER, new IdMessage(id));
    }

    public List<OwnerDto> getAll() {
        return ownerClient.getAll();
    }
}