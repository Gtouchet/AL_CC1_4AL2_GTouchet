package esgi.al.cc1.application.enumerators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Command
{
    // Contractor commands
    createContractor("CREATECONTRACTOR", 0, 0, "usage cr co"),
    readContractor("SELECTCONTRACTOR", 0, 0, "usage re co"),
    deleteContractor("DELETECONTRACTOR", 0, 0, "usage de co\n"),

    // Payment commands
    createPayment("CREATEPAYMENT", 0, 0, "usage cr pa"),
    readPayment("SELECTPAYMENT", 1, 2, "SELECTPAYMENT -> all payments\nSELECTPAYMENT id -> specific payment"),
    deletePayment("DELETEPAYMENT", 0, 0, "usage de pa\n"),

    // Project commands
    createProject("CREATEPROJECT", 0, 0, "usage cr pr"),
    readProject("SELECTPROJECT", 0, 0, "usage re pr"),
    deleteProject("DELETEPROJECT", 0, 0, "usage de pr\n"),

    // Worker commands
    createWorker("CREATEWORKER", 6, 0, "CREATEWORKER login password name service departement"),
    readWorker("SELECTWORKER", 1, 2, "SELECTWORKER -> all workers\nSELECTWORKER id -> specific worker"),
    updateWorker("UPDATEWORKER", 6, 0, "UPDATEWORKER id password name service departement"),
    deleteWorker("DELETEWORKER", 2, 0, "DELETEWORKER id\n"),

    // User commands
    updatePassword("UPDATEPASSWORD", 3, 0, "usage us uppass"),
    updateName("UPDATENAME", 3, 0, "usage us upname\n"),

    // Help command
    help("HELP", 2, 0, "HELP commandKeyword -> get command's usage"),

    // Quit command
    quit("QUIT", 0, 0, "QUIT -> quit the application"),

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
