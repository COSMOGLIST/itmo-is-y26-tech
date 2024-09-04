package ru.itmo.services;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.itmo.messages.DropFriendshipMessage;
import ru.itmo.messages.IdMessage;
import ru.itmo.messages.CreateFriendshipMessage;
import ru.itmo.messages.NewOwnerMessage;
import ru.itmo.models.CatDto;
import ru.itmo.rabbit.QueueCommands;

@Service
@EnableRabbit
public class CatServiceRabbitImpl implements CatServiceRabbit {
    private final CatService catService;

    public CatServiceRabbitImpl(CatService catService) {
        this.catService = catService;
    }

    @RabbitListener(queues = QueueCommands.NEW_CAT)
    public void creation(CatDto catDto) {
        catService.creation(catDto);
    }

    @RabbitListener(queues = QueueCommands.DELETE_CAT)
    public void deleteById(IdMessage idMessage) {
        int id = idMessage.getId();
        catService.deleteById(id);
    }

    @RabbitListener(queues = QueueCommands.NEW_FRIENDS)
    public void makeFriends(CreateFriendshipMessage createFriendshipMessage) {
        int id1 = createFriendshipMessage.getId1();
        int id2 = createFriendshipMessage.getId2();
        catService.makeFriends(id1, id2);
    }

    @RabbitListener(queues = QueueCommands.DROP_FRIENDS)
    public void dropFriends(DropFriendshipMessage dropFriendshipMessage) {
        int id1 = dropFriendshipMessage.getId1();
        int id2 = dropFriendshipMessage.getId2();
        catService.dropFriends(id1, id2);
    }

    @RabbitListener(queues = QueueCommands.CHANGE_OWNER)
    public void changeOwner(NewOwnerMessage newOwnerMessage) {
        int catId = newOwnerMessage.getCatId();
        int ownerId = newOwnerMessage.getOwnerId();
        catService.changeOwner(catId, ownerId);
    }
}
