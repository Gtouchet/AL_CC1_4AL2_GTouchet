package esgi.al.cc1.application.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Command
{
    // Payment commands
    createPayment("CREATEPAYMENT", 0, 0, "usage cr pa"),
    readPayment("SELECTPAYMENT", 1, 2, "usage re pa"),
    deletePayment("DELETEPAYMENT", 0, 0, "usage de pa"),

    // Project commands
    createProject("CREATEPROJECT", 0, 0, "usage cr pr"),
    readProject("SELECTPROJECT", 0, 0, "usage re pr"),
    deleteProject("DELETEPROJECT", 0, 0, "usage de pr"),

    // Tradesman commands
    createTradesman("CREATETRADESMAN", 0, 0, "usage cr tr"),
    readTradesman("SELECTTRADESMAN", 0, 0, "usage re tr"),
    deleteTradesman("DELETETRADESMAN", 0, 0, "usage de tr"),

    // Worker commands
    createWorker("CREATEWORKER", 0, 0, "usage cr wo"),
    readWorker("SELECTWORKER", 0, 0, "usage re wo"),
    deleteWorker("DELETEWORKER", 0, 0, "usage de wo"),

    // Quit command
    quit("QUIT", 0, 0, null),

    // None command
    none(null, 0, 0, null);

    public final String keyword;
    public final int parametersCount;
    public final int parametersOverloadCount;
    public final String usage;

    Command(String keyword, int parametersCount, int parametersOverloadCount, String usage)
    {
        this.keyword = keyword;
        this.parametersCount = parametersCount;
        this.parametersOverloadCount = parametersOverloadCount;
        this.usage = usage;
    }

    public static List<Command> getCommands()
    {
        return new ArrayList<>(Arrays.asList(Command.values()));
    }

    public static Command getCommand(String keyword)
    {
        return Command.getCommands().stream()
                .filter(command -> keyword.equals(command.keyword))
                .findFirst()
                .orElse(none);
    }
}
