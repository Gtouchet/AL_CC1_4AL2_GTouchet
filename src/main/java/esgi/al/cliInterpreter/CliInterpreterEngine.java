package esgi.al.cliInterpreter;

import esgi.al.cliInterpreter.cliCommandHandlers.CreateCommandHandler;
import esgi.al.cliInterpreter.cliCommandHandlers.DeleteCommandHandler;
import esgi.al.cliInterpreter.cliCommandHandlers.GetCommandHandler;
import esgi.al.cliInterpreter.cliCommandHandlers.UpdateCommandHandler;
import esgi.al.factories.ControllersFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CliInterpreterEngine
{
    private final ControllersFactory controllersFactory;

    private final Scanner scanner;
    private final List<Command> commands;
    private final List<Table> tables;

    public CliInterpreterEngine(ControllersFactory controllersFactory)
    {
        this.controllersFactory = controllersFactory;

        this.scanner = new Scanner(System.in);
        this.commands = Arrays.asList(Command.values());
        this.tables = Arrays.asList(Table.values());

        this.displayCommandsList();
    }

    private void displayCommandsList()
    {
        System.out.println(
                "---Available commands---\n" +
                "CREATE user login password name paymentMethod number street city\n" +
                "CREATE payment userLogin amount reason\n" +
                "GET user/payment\n" +
                "GET id user/payment\n" +
                "UPDATE user id login password name paymentMethod number street city\n" +
                "DELETE user/payment id\n" +
                "QUIT\n" +
                "---Command input---"
        );
    }

    public void launch()
    {
        String command = "";
        while (!command.trim().split(" ")[0].equalsIgnoreCase("QUIT"))
        {
            System.out.print("-> ");
            command = this.scanner.nextLine();
            processCommand(command);
        }
    }

    private void processCommand(String command)
    {
        String[] params = command.split(" ");

        if (!params[0].equalsIgnoreCase("QUIT"))
        {
            if (!this.commands.contains(Command.getFromString(params[0].toUpperCase())))
            {
                System.out.println("Unrecognized command [" + params[0].toUpperCase() + "]");
            }
            if (params.length < 2)
            {
                System.out.println("Please specify the command and table you want to execute. (ex: CREATE USER ...)");
            }
            else
            {
                if (!this.tables.contains(Table.getFromString(params[1].toUpperCase())))
                {
                    System.out.println("Unrecognized table [" + params[1].toUpperCase() + "]");
                }
                else
                {
                    switch (params[0].toUpperCase())
                    {
                        case "CREATE":
                            new CreateCommandHandler(
                                    this.controllersFactory.createUserController(),
                                    this.controllersFactory.createPaymentController(),
                                    params);
                            break;

                        case "GET":
                            new GetCommandHandler(
                                    this.controllersFactory.createUserController(),
                                    this.controllersFactory.createPaymentController(),
                                    params);
                        break;

                        case "UPDATE":
                            new UpdateCommandHandler(
                                    this.controllersFactory.createUserController(),
                                    params);
                        break;

                        case "DELETE":
                            new DeleteCommandHandler(
                                    this.controllersFactory.createUserController(),
                                    this.controllersFactory.createPaymentController(),
                                    params);
                        break;
                    }
                }
            }
        }
        else
        {
            System.out.println("See ya !");
        }
    }
}
