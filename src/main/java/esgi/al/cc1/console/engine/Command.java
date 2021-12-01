package esgi.al.cc1.console.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Command
{
    // Contractor commands
    createContractor("CREATECONTRACTOR", 5, "CREATECONTRACTOR login password name paymentMethod"),
    readContractor("SELECTCONTRACTOR", 1, "SELECTCONTRACTOR -> all contractors\nSELECTCONTRACTOR id -> specific contractor"),
    updateContractor("UPDATECONTRACTOR", 5, "UPDATECONTRACTOR id password name paymentMethod"),
    deleteContractor("DELETECONTRACTOR", 2, "DELETECONTRACTOR id"),
    validatePayment("VALIDATEPAYMENT", 2, "VALIDATEPAYMENT id -> validate the contractor's payment method\n"),

    // Payment commands
    createPayment("CREATEPAYMENT", 5, "CREATEPAYMENT contractorId workerId amount reason"),
    readPayment("SELECTPAYMENT", 1, "SELECTPAYMENT -> all payments\nSELECTPAYMENT id -> specific payment"),
    /* No update command for payments */
    deletePayment("DELETEPAYMENT", 2, "DELETEPAYMENT id\n"),

    // Project commands
    createProject("CREATEPROJECT", 3, "CREATEPROJECT contractorId department"),
    readProject("SELECTPROJECT", 1, "SELECTPROJECT -> all projects\nSELECTPROJECT id -> specific project"),
    updateProject("UPDATEPROJECT", 4, "UPDATEPROJECT id contractorId department"),
    deleteProject("DELETEPROJECT", 2, "DELETEPROJECT id"),
    engageWorker("ENGAGEWORKER", 3, "ENGAGEWORKER workerId projectId -> add a worker to a project"),
    fireWorker("FIREWORKER", 3, "FIREWORKER workerId projectId -> remove a worker from a project\n"),

    // Worker commands
    createWorker("CREATEWORKER", 6, "CREATEWORKER login password name service department"),
    readWorker("SELECTWORKER", 1, "SELECTWORKER -> all workers\nSELECTWORKER id -> specific worker"),
    updateWorker("UPDATEWORKER", 6, "UPDATEWORKER id newPassword newName newService newDepartment"),
    deleteWorker("DELETEWORKER", 2, "DELETEWORKER id\n"),

    // Help command
    help("HELP", 1, "HELP -> get all commands and their usages\nHELP command -> get specific command's usage"),

    // Quit command
    quit("QUIT", 0, "QUIT -> quit the application"),

    // None command (prevents a crash in case of unknown command)
    none(null, 0, null);

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
                .orElse(none);
    }
}
