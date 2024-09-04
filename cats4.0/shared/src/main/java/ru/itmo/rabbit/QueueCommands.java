package ru.itmo.rabbit;

public interface QueueCommands {
    String NEW_OWNER = "newOwner";
    String DELETE_OWNER = "deleteOwner";
    String NEW_CAT = "newCat";
    String DELETE_CAT = "deleteCat";
    String NEW_FRIENDS = "newFriends";
    String DROP_FRIENDS = "dropFriends";
    String CHANGE_OWNER = "changeOwner";
}