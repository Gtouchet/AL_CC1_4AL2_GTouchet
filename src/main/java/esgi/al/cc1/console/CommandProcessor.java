package esgi.al.cc1.console;

import esgi.al.cc1.console.commandHandlers.CreateCommandHandler;
import esgi.al.cc1.console.commandHandlers.DeleteCommandHandler;
import esgi.al.cc1.console.commandHandlers.GetCommandHandler;
import esgi.al.cc1.console.commandHandlers.UpdateCommandHandler;
import esgi.al.cc1.console.enumerators.CommandKeyword;
import esgi.al.cc1.console.enumerators.TableName;
import esgi.al.cc1.factories.ControllersFactory;

import java.util.List;
import java.util.Objects;

public class CommandProcessor
{
    private final ControllersFactory controllersFactory;

    private final List<CommandKeyword> commands;
    private final List<TableName> tables;

    public CommandProcessor(ControllersFactory controllersFactory)
    {
        this.controllersFactory = controllersFactory;

        this.commands = CommandKeyword.getCommandKeywords();
        this.tables = TableName.getTableNames();
    }

    public void process(String command)
    {
        String[] params = command.split(" ");

        if (!params[0].equalsIgnoreCase(CommandKeyword.QUIT.toString()))
        {
            if (params[0].equals(""))
            {
                return;
            }

            if (!this.commands.contains(CommandKeyword.stringToCommand(params[0].toUpperCase())))
            {
                System.out.println("Unrecognized command [" + params[0].toUpperCase() + "]");
            }
            else if (params.length < 2)
            {
                System.out.println("Please specify the command and table you want to execute. (ex: CREATE USER ...)");
            }
            else
            {
                if (!this.tables.contains(TableName.stringToTable(params[1].toUpperCase())))
                {
                    System.out.println("Unrecognized table [" + params[1].toUpperCase() + "]");
                }
                else
                {
                    switch (Objects.requireNonNull(CommandKeyword.stringToCommand(params[0].toUpperCase())))
                    {
                        case CREATE:
                            new CreateCommandHandler(
                                    this.controllersFactory.createUserController(),
                                    this.controllersFactory.createPaymentController()
                            ).handle(params);
                            break;

                        case GET:
                            new GetCommandHandler(
                                    this.controllersFactory.createUserController(),
                                    this.controllersFactory.createPaymentController()
                            ).handle(params);
                            break;

                        case UPDATE:
                            new UpdateCommandHandler(
                                    this.controllersFactory.createUserController()
                            ).handle(params);
                            break;

                        case DELETE:
                            new DeleteCommandHandler(
                                    this.controllersFactory.createUserController(),
                                    this.controllersFactory.createPaymentController()
                            ).handle(params);
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
