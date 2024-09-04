package ru.itmo.services;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.itmo.messages.IdMessage;
import ru.itmo.models.OwnerDto;
import ru.itmo.rabbit.QueueCommands;

@Service
@EnableRabbit
public class OwnerServiceRabbitImpl implements OwnerServiceRabbit {

    private final OwnerService ownerService;

    public OwnerServiceRabbitImpl(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RabbitListener(queues = QueueCommands.NEW_OWNER)
    public void creation(OwnerDto ownerDto) {
        ownerService.creation(ownerDto);
    }

    @RabbitListener(queues = QueueCommands.DELETE_OWNER)
    public void deleteById(IdMessage idMessage) {
        ownerService.deleteById(idMessage.getId());
    }
}
