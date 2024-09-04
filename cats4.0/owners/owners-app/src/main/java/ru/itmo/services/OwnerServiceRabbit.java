package ru.itmo.services;

import ru.itmo.messages.IdMessage;
import ru.itmo.models.OwnerDto;

public interface OwnerServiceRabbit {
    void creation(OwnerDto ownerDto);


    void deleteById(IdMessage idMessage);
}
