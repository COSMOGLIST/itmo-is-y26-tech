package ru.itmo.commands;
import picocli.CommandLine;
import ru.itmo.controllers.CatController;
import ru.itmo.controllers.OwnerController;
import ru.itmo.dto.CatDto;
import ru.itmo.dto.OwnerDto;

import java.time.LocalDate;

@CommandLine.Command(name = "Main menu", mixinStandardHelpOptions = true)
public class MainMenu {
    private final CatController catController;
    private final OwnerController ownerController;

    public MainMenu(CatController catController, OwnerController ownerController) {
        this.catController = catController;
        this.ownerController = ownerController;
    }

    @CommandLine.Command(name = "newOwner", description = "Create new owner")
    public void newOwner(
            @CommandLine.Option(names = {"n", "name"}, required = true, description = "owner name") String name,
            @CommandLine.Option(names = {"bd", "birthdate"}, required = true, description = "owner birth date")LocalDate birthdate) {
        OwnerDto ownerDto = ownerController.creation(name, birthdate);
        System.out.println("Owner " + name + " with id = " + ownerDto.getId() + " created.");
    }

    @CommandLine.Command(name = "newCat", description = "Create new cat")
    public void newCat(
            @CommandLine.Option(names = {"n", "name"}, required = true, description = "cat name") String name,
            @CommandLine.Option(names = {"bd", "birthdate"}, required = true, description = "cat birth date")LocalDate birthdate,
            @CommandLine.Option(names = {"b", "breed"}, required = true, description = "cat breed") String breed,
            @CommandLine.Option(names = {"c", "color"}, required = true, description = "cat color") String color,
            @CommandLine.Option(names = {"id", "ID"}, description = "owner id") int ownerId) {
        CatDto catDto = catController.creation(name, birthdate, breed, color, ownerId);
        System.out.println("Cat " + name + " with id = " + catDto.getId() + " created.");
    }

    @CommandLine.Command(name = "newFriends", description = "Make new friends")
    public void newFriends(
            @CommandLine.Option(names = {"c1", "cat1"}, required = true, description = "cat 1") int id1,
            @CommandLine.Option(names = {"c2", "cat2"}, required = true, description = "cat 2") int id2) {
        catController.makeFriends(id1, id2);
        CatDto cat1 = catController.findById(id1);
        CatDto cat2 = catController.findById(id2);
        System.out.println("Cat " + cat1.getName() + " with id = " + cat1.getId() + " and cat " + cat2.getName() + " with id = " + cat2.getId() + " are friends now.");
    }

    @CommandLine.Command(name = "dropFriends", description = "drops friends")
    public void dropFriends(
            @CommandLine.Option(names = {"c1", "cat1"}, required = true, description = "cat 1") int id1,
            @CommandLine.Option(names = {"c2", "cat2"}, required = true, description = "cat 2") int id2) {
        catController.dropFriends(id1, id2);
        CatDto cat1 = catController.findById(id1);
        CatDto cat2 = catController.findById(id2);
        System.out.println("Cat " + cat1.getName() + " with id = " + cat1.getId() + " and cat " + cat2.getName() + " with id = " + cat2.getId() + " are not friends anymore.");
    }

    @CommandLine.Command(name = "changeOwner")
    public void changeOwner(
            @CommandLine.Option(names = {"o", "owner"}, required = true) int ownerId,
            @CommandLine.Option(names = {"c", "cat"}, required = true) int catId) {
        CatDto cat = catController.findById(catId);
        OwnerDto owner = ownerController.findById(ownerId);
        catController.changeOwner(catId, ownerId);
        System.out.println("Cat " + cat.getName() + " with id = " + cat.getId() + " belongs to " + owner.getName() + " with id = " + owner.getId() + " now.");
    }

    @CommandLine.Command(name = "deleteCat", description = "deletes cat")
    public void deleteCat(
            @CommandLine.Option(names = {"c", "cat"}, required = true) int catId) {
        CatDto cat = catController.findById(catId);
        catController.delete(cat);
        System.out.println("Cat " + cat.getName() + " with id = " + cat.getId() + " no longer exists.");
    }

    @CommandLine.Command(name = "deleteOwner", description = "deletes owner")
    public void deleteOwner(
            @CommandLine.Option(names = {"o", "owner"}, required = true) int ownerId) {
        OwnerDto owner = ownerController.findById(ownerId);
        ownerController.delete(owner);
        System.out.println("Owner " + owner.getName() + " with id = " + owner.getId() + " no longer exists.");
    }

    @CommandLine.Command(name = "infoOwner", description = "gets info about owner")
    public void infoOwner(
            @CommandLine.Option(names = {"o", "owner"}, required = true) int ownerId) {
        OwnerDto owner = ownerController.findById(ownerId);
        System.out.println("Name: " + owner.getName() + "\nId: " + owner.getId() + "\n Birth date: " + owner.getBirthDate());
    }
    @CommandLine.Command(name = "infoCat", description = "gets info about cat")
    public void infoCat(
            @CommandLine.Option(names = {"c", "cat"}, required = true) int catId) {
        CatDto cat = catController.findById(catId);
        System.out.println(
                "Name: " + cat.getName() +
                "\nId: " + cat.getId() +
                "\nBirth date: " + cat.getBirthDate() +
                "\nBreed: " + cat.getBreed() +
                "\nColor: " + cat.getColor() +
                "\nOwnerId " + cat.getOwnerId());
    }

}
