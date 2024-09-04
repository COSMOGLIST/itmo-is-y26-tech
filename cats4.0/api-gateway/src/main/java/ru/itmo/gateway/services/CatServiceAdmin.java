package ru.itmo.gateway.services;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.itmo.CatClient;
import ru.itmo.messages.DropFriendshipMessage;
import ru.itmo.messages.IdMessage;
import ru.itmo.messages.CreateFriendshipMessage;
import ru.itmo.messages.NewOwnerMessage;
import ru.itmo.models.CatDto;
import ru.itmo.rabbit.QueueCommands;
import java.util.List;

@Service
public class CatServiceAdmin {
    private final RabbitTemplate rabbitTemplate;
    private final CatClient catClient;

    public CatServiceAdmin(RabbitTemplate rabbitTemplate, CatClient catClient) {
        this.rabbitTemplate = rabbitTemplate;
        this.catClient = catClient;
    }

    public void createCat(CatDto catDto) {
        rabbitTemplate.convertAndSend(QueueCommands.NEW_CAT, catDto);
    }

    public void deleteById(int id) {
        rabbitTemplate.convertAndSend(QueueCommands.DELETE_CAT, new IdMessage(id));
    }

    public void makeFriends(int cat1Id, int cat2Id) {
        rabbitTemplate.convertAndSend(QueueCommands.NEW_FRIENDS, new CreateFriendshipMessage(cat1Id, cat2Id));
    }

    public void dropFriends(int cat1Id, int cat2Id) {
        rabbitTemplate.convertAndSend(QueueCommands.DROP_FRIENDS, new DropFriendshipMessage(cat1Id, cat2Id));
    }

    public void changeOwner(int catId, int newOwnerId) {
        rabbitTemplate.convertAndSend(QueueCommands.CHANGE_OWNER, new NewOwnerMessage(catId, newOwnerId));
    }

    public CatDto findById(int id) {
        return catClient.findById(id);
    }

    public List<CatDto> getCatsByCriteria(String color, String breed, String id, String ownerId) {
        return catClient.getCatsByCriteria(color, breed, id, ownerId);
    }
}