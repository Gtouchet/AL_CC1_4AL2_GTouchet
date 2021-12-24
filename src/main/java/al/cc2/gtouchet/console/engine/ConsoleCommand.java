package al.cc2.gtouchet.console.engine;

import al.cc2.gtouchet.console.handlers.contractor.*;
import al.cc2.gtouchet.console.handlers.miscellaneous.HelpConsoleHandler;
import al.cc2.gtouchet.console.handlers.payment.*;
import al.cc2.gtouchet.console.handlers.project.*;
import al.cc2.gtouchet.console.handlers.worker.*;
import al.cc2.gtouchet.application.services.handlers.contractor.*;
import al.cc2.gtouchet.application.services.handlers.payment.*;
import al.cc2.gtouchet.application.services.handlers.project.*;
import al.cc2.gtouchet.application.services.handlers.worker.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ConsoleCommand
{
    // Contractor commands
    CREATE_CONTRACTOR("CREATECONTRACTOR", 5,
            "CREATECONTRACTOR login password name paymentMethod",
            CreateContractorConsoleHandler.class,
            new Class[] { CreateContractorCommandHandler.class }),
    READ_CONTRACTOR("SELECTCONTRACTOR", 1,
            "SELECTCONTRACTOR -> all contractors\nSELECTCONTRACTOR id -> specific contractor",
            ReadContractorConsoleHandler.class,
            new Class[] { ReadAllContractorQueryHandler.class, ReadContractorQueryHandler.class }),
    UPDATE_CONTRACTOR("UPDATECONTRACTOR", 5,
            "UPDATECONTRACTOR id password name paymentMethod",
            UpdateContractorConsoleHandler.class,
            new Class[] { UpdateContractorCommandHandler.class }),
    DELETE_CONTRACTOR("DELETECONTRACTOR", 2,
            "DELETECONTRACTOR id",
            DeleteContractorConsoleHandler.class,
            new Class[] { DeleteContractorCommandHandler.class }),
    VALIDATE_PAYMENT("VALIDATEPAYMENT", 2,
            "VALIDATEPAYMENT id -> validate the contractor's payment method\n",
            ValidatePaymentConsoleHandler.class,
            new Class[] { ValidatePaymentCommandHandler.class }),

    // Payment commands
    CREATE_PAYMENT("CREATEPAYMENT", 5,
            "CREATEPAYMENT contractorId workerId amount reason",
            CreatePaymentConsoleHandler.class,
            new Class[] { CreatePaymentCommandHandler.class }),
    READ_PAYMENT("SELECTPAYMENT", 1,
            "SELECTPAYMENT -> all payments\nSELECTPAYMENT id -> specific payment",
            ReadPaymentConsoleHandler.class,
            new Class[] { ReadAllPaymentQueryHandler.class, ReadPaymentQueryHandler.class }),
    DELETE_PAYMENT("DELETEPAYMENT", 2,
            "DELETEPAYMENT id\n",
            DeletePaymentConsoleHandler.class,
            new Class[] { DeletePaymentCommandHandler.class }),

    // Project commands
    CREATE_PROJECT("CREATEPROJECT", 3,
            "CREATEPROJECT contractorId department",
            CreateProjectConsoleHandler.class,
            new Class[] { CreateProjectCommandHandler.class }),
    READ_PROJECT("SELECTPROJECT", 1,
            "SELECTPROJECT -> all projects\nSELECTPROJECT id -> specific project",
            ReadProjectConsoleHandler.class,
            new Class[] { ReadAllProjectQueryHandler.class, ReadProjectQueryHandler.class }),
    UPDATE_PROJECT("UPDATEPROJECT", 4,
            "UPDATEPROJECT id contractorId department",
            UpdateProjectConsoleHandler.class,
            new Class[] { UpdateProjectCommandHandler.class }),
    DELETE_PROJECT("DELETEPROJECT", 2,
            "DELETEPROJECT id",
            DeleteProjectConsoleHandler.class,
            new Class[] { DeleteProjectCommandHandler.class }),
    ENGAGE_WORKER("ENGAGEWORKER", 3,
            "ENGAGEWORKER workerId projectId -> add a worker to a project",
            EngageWorkerConsoleHandler.class,
            new Class[] { EngageWorkerCommandHandler.class }),
    FIRE_WORKER("FIREWORKER", 3,
            "FIREWORKER workerId projectId -> remove a worker from a project\n",
            FireWorkerConsoleHandler.class,
            new Class[] { FireWorkerCommandHandler.class }),

    // Worker commands
    CREATE_WORKER("CREATEWORKER", 6,
            "CREATEWORKER login password name service department",
            CreateWorkerConsoleHandler.class,
            new Class[] { CreateWorkerCommandHandler.class }),
    READ_WORKER("SELECTWORKER", 1,
            "SELECTWORKER -> all workers\nSELECTWORKER id -> specific worker",
            ReadWorkerConsoleHandler.class,
            new Class[] { ReadAllWorkerQueryHandler.class, ReadWorkerQueryHandler.class }),
    UPDATE_WORKER("UPDATEWORKER", 6,
            "UPDATEWORKER id newPassword newName newService newDepartment",
            UpdateWorkerConsoleHandler.class,
            new Class[] { UpdateWorkerCommandHandler.class }),
    DELETE_WORKER("DELETEWORKER", 2,
            "DELETEWORKER id\n",
            DeleteWorkerConsoleHandler.class,
            new Class[] { DeleteWorkerCommandHandler.class }),

    // Help command
    HELP("HELP", 1,
            "HELP -> get all commands and their usages\nHELP command -> get specific command's usage",
            HelpConsoleHandler.class,
            null),

    // Quit command
    QUIT("QUIT", 0, "QUIT -> quit the application", null, null),

    // None command (prevents a crash in case of unknown command)
    NONE(null, 0, null, null, null);

    public final String keyword;
    public final int parameters;
    public final String usage;
    public final Class consoleHandler;
    public final Class[] serviceHandlers;

    ConsoleCommand(String keyword, int parameters, String usage, Class consoleHandler, Class[] serviceHandlers)
    {
        this.keyword = keyword;
        this.parameters = parameters;
        this.usage = usage;
        this.consoleHandler = consoleHandler;
        this.serviceHandlers = serviceHandlers;
    }

    public static List<ConsoleCommand> getCommands()
    {
        return new ArrayList<>(Arrays.asList(ConsoleCommand.values()));
    }

    public static ConsoleCommand getCommand(String keyword)
    {
        return ConsoleCommand.getCommands().stream()
                .filter(command -> keyword.equalsIgnoreCase(command.keyword))
                .findFirst()
                .orElse(NONE);
    }
}
