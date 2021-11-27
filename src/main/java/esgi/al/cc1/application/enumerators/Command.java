package esgi.al.cc1.application.enumerators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Command
{
    // Contractor commands
    createContractor("CREATECONTRACTOR", 0, 0, "usage cr co"),
    readContractor("SELECTCONTRACTOR", 0, 0, "usage re co"),
    deleteContractor("DELETECONTRACTOR", 0, 0, "usage de co"),

    // Payment commands
    createPayment("CREATEPAYMENT", 0, 0, "usage cr pa"),
    readPayment("SELECTPAYMENT", 1, 2, "usage re pa"),
    deletePayment("DELETEPAYMENT", 0, 0, "usage de pa"),

    // Project commands
    createProject("CREATEPROJECT", 0, 0, "usage cr pr"),
    readProject("SELECTPROJECT", 0, 0, "usage re pr"),
    deleteProject("DELETEPROJECT", 0, 0, "usage de pr"),

    // Worker commands
    createWorker("CREATEWORKER", 6, 0, "CREATEWORKER login password name service departement"),
    readWorker("SELECTWORKER", 1, 2, "usage re wo"),
    deleteWorker("DELETEWORKER", 2, 0, "usage de wo"),

    // Help command
    help("HELP", 2, 0, "HELP commandKeyword"),

    // Quit command
    quit("QUIT", 0, 0, "QUIT"),

    // None command (prevents a crash in case of unknown command)
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
                .filter(command -> keyword.equalsIgnoreCase(command.keyword))
                .findFirst()
                .orElse(none);
    }
}
