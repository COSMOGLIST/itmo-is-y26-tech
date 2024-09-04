package ru.itmo.services;

import ru.itmo.messages.DropFriendshipMessage;
import ru.itmo.messages.IdMessage;
import ru.itmo.messages.CreateFriendshipMessage;
import ru.itmo.messages.NewOwnerMessage;
import ru.itmo.models.CatDto;

public interface CatServiceRabbit {

    void creation(CatDto catDto);


    void deleteById(IdMessage messageId);


    void makeFriends(CreateFriendshipMessage createFriendshipMessage);


    void dropFriends(DropFriendshipMessage dropFriendshipMessage);


    void changeOwner(NewOwnerMessage newOwnerMessage);
}
