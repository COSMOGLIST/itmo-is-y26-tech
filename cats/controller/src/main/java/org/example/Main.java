package org.example;

import picocli.CommandLine;
import ru.itmo.commands.CatExceptionHandler;
import ru.itmo.commands.MainMenu;
import ru.itmo.controllers.CatController;
import ru.itmo.controllers.CatControllerImpl;
import ru.itmo.controllers.OwnerController;
import ru.itmo.controllers.OwnerControllerImpl;
import ru.itmo.dao.CatDao;
import ru.itmo.dao.CatDaoImp;
import ru.itmo.dao.OwnerDao;
import ru.itmo.dao.OwnerDaoImp;
import ru.itmo.services.CatService;
import ru.itmo.services.CatServiceImpl;
import ru.itmo.services.OwnerService;
import ru.itmo.services.OwnerServiceImpl;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CatDao catDao = new CatDaoImp();
        OwnerDao ownerDao = new OwnerDaoImp();
        CatService catService = new CatServiceImpl(catDao, ownerDao);
        OwnerService ownerService = new OwnerServiceImpl(catDao, ownerDao);
        OwnerController ownerController = new OwnerControllerImpl(ownerService);
        CatController catController = new CatControllerImpl(catService);
        MainMenu mainMenu = new MainMenu(catController, ownerController);
        CommandLine commandLine = new CommandLine(mainMenu);
        System.out.println(
                """
                        Welcome to cat and owners menu!
                        Print --help for commands or print --quit to quit program""");
        while (true) {
            String line = (new Scanner(System.in)).nextLine();
            if (Objects.equals(line, "--quit")) {
                break;
            } else {
                commandLine.setExecutionExceptionHandler(new CatExceptionHandler()).execute(line.split(" "));
            }
        }

    }
}
