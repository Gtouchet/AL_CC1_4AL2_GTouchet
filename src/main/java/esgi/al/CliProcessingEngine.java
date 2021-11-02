package esgi.al;

import esgi.al.controllers.UserController;
import esgi.al.daos.AddressDao;
import esgi.al.daos.UserDao;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CliProcessingEngine
{
    private final UserController userController;

    private final Scanner scanner;
    private final Map<Integer, String> commandsExs;

    public CliProcessingEngine(UserController userController)
    {
        this.userController = userController;

        this.scanner = new Scanner(System.in);
        this.commandsExs = this.initCommandExs();
    }

    private Map<Integer, String> initCommandExs()
    {
        Map<Integer, String> commandsExs = new HashMap<>();

        commandsExs.put(0, "---Available commands---");
        commandsExs.put(1, "CREATE login password name paymentMethod number street city");
        commandsExs.put(2, "GET");
        commandsExs.put(3, "GET id");
        commandsExs.put(4, "UPDATE id login password name paymentMethod number street city");
        commandsExs.put(5, "DELETE id");
        commandsExs.put(6, "QUIT");
        commandsExs.put(7, "---Command input---");

        commandsExs.values().forEach(System.out::println);

        return commandsExs;
    }

    public void launch()
    {
        String command = "";
        while (!command.trim().equalsIgnoreCase("QUIT"))
        {
            System.out.print("-> ");
            command = this.scanner.nextLine();
            processCommand(command);
        }
    }

    private void processCommand(String command)
    {
        try {
            String[] params = command.split(" ");

            switch (params[0].toUpperCase())
            {
                case "CREATE": this.processCreateCommand(params); break;
                case "GET":    this.processGetCommand(params); break;
                case "UPDATE": this.processUpdateCommand(params); break;
                case "DELETE": this.processDeleteCommand(params); break;
                case "QUIT":   System.out.println("See ya !"); break;

                default: System.out.println("Unrecognized command [" + params[0].toUpperCase() + "]"); break;
            }
        } catch (FailedToCreate e) {
            e.printStackTrace();
        }
    }

    private void processCreateCommand(String[] params) throws FailedToCreate
    {
        if (params.length != 8)
        {
            System.out.println(this.commandsExs.get(1));
            return;
        }

        int streetNumber = 0;
        try {
            streetNumber = Integer.parseInt(params[5]);
        } catch (NumberFormatException ignored) {
            System.err.println("Impossible to parse [" + params[5] + "] as Integer");
        }

        this.userController.post(
                new UserDao(null, params[1], params[2], params[3], params[4].trim().toLowerCase(),
                new AddressDao(streetNumber, params[6], params[7]))
        );
    }

    private void processGetCommand(String[] params)
    {
        if (params.length == 1)
        {
            this.userController.get().forEach(System.out::println);
        }
        else if (params.length == 2)
        {
            System.out.println(this.userController.get(params[1].toLowerCase()));
        }
        else
        {
            System.out.println(this.commandsExs.get(2) + "\n" + this.commandsExs.get(3));
        }
    }

    private void processUpdateCommand(String[] params)
    {
        if (params.length != 9)
        {
            System.out.println(this.commandsExs.get(4));
            return;
        }

        int streetNumber = 0;
        try {
            streetNumber = Integer.parseInt(params[6]);
        } catch (NumberFormatException ignored) {
            System.err.println("Impossible to parse [" + params[5] + "] as Integer");
        }

        this.userController.put(params[1].toLowerCase(),
                new UserDao(null, params[2], params[3], params[4], params[5].trim().toLowerCase(),
                new AddressDao(streetNumber, params[7], params[8]))
        );
    }

    private void processDeleteCommand(String[] params)
    {
        if (params.length != 2)
        {
            System.out.println(this.commandsExs.get(5));
            return;
        }

        this.userController.del(params[1].toLowerCase());
    }
}
