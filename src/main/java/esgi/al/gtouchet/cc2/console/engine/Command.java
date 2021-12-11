package esgi.al.gtouchet.cc2.console.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Command
{
    // Contractor commands
    CREATE_CONTRACTOR("CREATECONTRACTOR", 5, "CREATECONTRACTOR login password name paymentMethod"),
    READ_CONTRACTOR("SELECTCONTRACTOR", 1, "SELECTCONTRACTOR -> all contractors\nSELECTCONTRACTOR id -> specific contractor"),
    UPDATE_CONTRACTOR("UPDATECONTRACTOR", 5, "UPDATECONTRACTOR id password name paymentMethod"),
    DELETE_CONTRACTOR("DELETECONTRACTOR", 2, "DELETECONTRACTOR id"),
    VALIDATE_PAYMENT("VALIDATEPAYMENT", 2, "VALIDATEPAYMENT id -> validate the contractor's payment method\n"),

    // Payment commands
    CREATE_PAYMENT("CREATEPAYMENT", 5, "CREATEPAYMENT contractorId workerId amount reason"),
    READ_PAYMENT("SELECTPAYMENT", 1, "SELECTPAYMENT -> all payments\nSELECTPAYMENT id -> specific payment"),
    /* No update command for payments */
    DELETE_PAYMENT("DELETEPAYMENT", 2, "DELETEPAYMENT id\n"),

    // Project commands
    CREATE_PROJECT("CREATEPROJECT", 3, "CREATEPROJECT contractorId department"),
    READ_PROJECT("SELECTPROJECT", 1, "SELECTPROJECT -> all projects\nSELECTPROJECT id -> specific project"),
    UPDATE_PROJECT("UPDATEPROJECT", 4, "UPDATEPROJECT id contractorId department"),
    DELETE_PROJECT("DELETEPROJECT", 2, "DELETEPROJECT id"),
    ENGAGE_WORKER("ENGAGEWORKER", 3, "ENGAGEWORKER workerId projectId -> add a worker to a project"),
    FIRE_WORKER("FIREWORKER", 3, "FIREWORKER workerId projectId -> remove a worker from a project\n"),

    // Worker commands
    CREATE_WORKER("CREATEWORKER", 6, "CREATEWORKER login password name service department"),
    READ_WORKER("SELECTWORKER", 1, "SELECTWORKER -> all workers\nSELECTWORKER id -> specific worker"),
    UPDATE_WORKER("UPDATEWORKER", 6, "UPDATEWORKER id newPassword newName newService newDepartment"),
    DELETE_WORKER("DELETEWORKER", 2, "DELETEWORKER id\n"),

    // Help command
    HELP("HELP", 1, "HELP -> get all commands and their usages\nHELP command -> get specific command's usage"),

    // Quit command
    QUIT("QUIT", 0, "QUIT -> quit the application"),

    // None command (prevents a crash in case of unknown command)
    NONE(null, 0, null);

    public final String keyword;
    public final int parameters;
    public final String usage;

    Command(String keyword, int parameters, String usage)
    {
        this.keyword = keyword;
        this.parameters = parameters;
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
                .orElse(NONE);
    }
}
