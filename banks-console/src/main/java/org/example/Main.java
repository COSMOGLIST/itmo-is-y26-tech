package org.example;

import ru.itmo.banks.CentralBank;
import ru.itmo.commands.BankExceptionHandler;
import ru.itmo.commands.MainMenu;
import ru.itmo.models.DayCounter;
import ru.itmo.models.IdCreator;
import ru.itmo.models.Workspace;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Workspace workspace = new Workspace(new CentralBank(new DayCounter()), new ArrayList<>(), new IdCreator());
        MainMenu controlChoose = new MainMenu(workspace);
        CommandLine commandLine = new CommandLine(controlChoose);
        System.out.println(
                """
                        Welcome to choose control!
                        Print --help for commands or print --quit to quit program""");
        while (true) {
            String line = (new Scanner(System.in)).nextLine();
            if (Objects.equals(line, "--quit")) {
                break;
            } else {
                commandLine.setExecutionExceptionHandler(new BankExceptionHandler()).execute(line.split(" "));
            }
        }

    }
}