package ru.itmo.commands;

import ru.itmo.models.Workspace;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

@CommandLine.Command(name = "Central bank controller", mixinStandardHelpOptions = true)
public class CentralBankController implements Runnable {
    private final Workspace workspace;

    public CentralBankController(Workspace workspace) {
        this.workspace = workspace;
    }

    @CommandLine.Command(name = "NewBank", description = "Create new bank")
    public void newBank(
            @CommandLine.Option(names = "name", required = true, description = "Bank name") String bankName,
            @CommandLine.Option(names = "d", required = true, description = "Percent for debit accounts") double debitPercent,
            @CommandLine.Option(names = "cc", required = true, description = "Commission for credit accounts") double creditCommission,
            @CommandLine.Option(names = "mt", required = true, description = "Maximum transaction for suspicious accounts") double maximumTransactionForSuspiciousAccounts
    ) {
        workspace.getCentralBank().createBank(bankName, debitPercent, creditCommission, maximumTransactionForSuspiciousAccounts, new ArrayList<>());
        System.out.println("Bank " + bankName + " created");
    }

    @CommandLine.Command(name = "NextDay", description = "A day passes")
    public void nextDay() {
        workspace.getCentralBank().endOfDay();
        System.out.println("1 day passed");
    }

    @CommandLine.Command(name = "BankControl", description = "Takes control of a bank")
    public void bankControl(String bankName) {
        CommandLine commandLine = new CommandLine(new BankController(workspace.getCentralBank().getBank(bankName)));
        System.out.println(
                """
                        Welcome to Bank control!
                        Print --help for commands or print --quit to return to Central Bank Control""");
        while (true) {
            String line = (new Scanner(System.in)).nextLine();
            if (Objects.equals(line, "--quit")) {
                break;
            } else {
                commandLine.setExecutionExceptionHandler(new BankExceptionHandler()).execute(line.split(" "));
            }
        }

    }

    @CommandLine.Command(name = "TimeMachine", description = "n days pass")
    public void timeMachine(int numberOfDays) {
        for (int i = 0; i < numberOfDays; i++) {
            workspace.getCentralBank().endOfDay();
        }
        System.out.println(numberOfDays + " days passed");
    }

    public void run() {
    }

}
