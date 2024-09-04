package ru.itmo.gateway.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.CatClient;
import ru.itmo.messages.CreateFriendshipMessage;
import ru.itmo.messages.DropFriendshipMessage;
import ru.itmo.models.CatDto;
import ru.itmo.rabbit.QueueCommands;
import ru.itmo.security.services.UserService;

import java.util.List;

import static java.lang.String.valueOf;

@Service
public class CatServiceUser {
    private final RabbitTemplate rabbitTemplate;

    private final CatClient catClient;
    private final UserService userService;

    @Autowired
    public CatServiceUser(RabbitTemplate rabbitTemplate, CatClient catClient, UserService userService) {
        this.rabbitTemplate = rabbitTemplate;
        this.catClient = catClient;
        this.userService = userService;
    }
    public CatDto findById(int id) {
        return catClient.findById(id);
    }

    public List<CatDto> getCatsByCriteria(String color, String breed, String id) {
        return catClient.getCatsByCriteria(color, breed, id, valueOf(userService.getCurrentUser().getOwnerId()));
    }
    public void createCat(CatDto catDto) {
        catDto.setOwnerId(userService.getCurrentUser().getOwnerId());
        rabbitTemplate.convertAndSend(QueueCommands.NEW_CAT, catDto);
    }

    public void makeFriends(int cat1Id, int cat2Id) {
        int ownerId = userService.getCurrentUser().getOwnerId();
        List<CatDto> cat1 = catClient.getCatsByCriteria(null, null, valueOf(cat1Id), valueOf(ownerId));
        List<CatDto> cat2 = catClient.getCatsByCriteria(null, null, valueOf(cat2Id), valueOf(ownerId));
        if (cat1.size() == 1 && cat2.size() == 1 && cat1.getFirst().getOwnerId() == ownerId && cat2.getFirst().getOwnerId() == ownerId) {
            rabbitTemplate.convertAndSend(QueueCommands.NEW_FRIENDS, new CreateFriendshipMessage(cat1Id, cat2Id));
        }
    }

    public void dropFriends(int cat1Id, int cat2Id) {
        int ownerId = userService.getCurrentUser().getOwnerId();
        List<CatDto> cat1 = catClient.getCatsByCriteria(null, null, valueOf(cat1Id), valueOf(ownerId));
        List<CatDto> cat2 = catClient.getCatsByCriteria(null, null, valueOf(cat2Id), valueOf(ownerId));
        if (cat1.size() == 1 && cat2.size() == 1 && cat1.getFirst().getOwnerId() == ownerId && cat2.getFirst().getOwnerId() == ownerId) {
            rabbitTemplate.convertAndSend(QueueCommands.DROP_FRIENDS, new DropFriendshipMessage(cat1Id, cat2Id));
        }
    }

}