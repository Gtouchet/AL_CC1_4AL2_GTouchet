package esgi.al.utils;

import esgi.al.controllers.UserController;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.exceptions.NoUserFound;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandInputHandler
{
    private final UserController userController;
    private final Scanner scanner;
    private final Map<Integer, String> commandsExs = new HashMap<>();

    public CommandInputHandler(UserController userController)
    {
        this.userController = userController;
        this.scanner = new Scanner(System.in);

        this.initializeCommandExamplesMap();
        this.displayCommandsExamples();
    }

    private void initializeCommandExamplesMap()
    {
        this.commandsExs.put(0,  "----- Available commands -----");
        this.commandsExs.put(1,  "Create a new user with values     -> CREATE login password name paymentMethod city streetType streetName streetNumber");
        this.commandsExs.put(2,  "Get all users in the repo         -> GETALL");
        this.commandsExs.put(3,  "Get a user under specified ID     -> GETBYID id");
        this.commandsExs.put(4,  "Get a user under specified login  -> GETBYLOGIN login");
        this.commandsExs.put(5,  "Get all users with specified name -> GETBYNAME name");
        this.commandsExs.put(6,  "Get all users using this payment  -> GETBYPAYMENT paymentMethod");
        this.commandsExs.put(7,  "Change the user's password        -> UPDATEPASSWORD method idOrLogin newPassword");
        this.commandsExs.put(8,  "Change the user's name            -> UPDATENAME method idOrLogin newName");
        this.commandsExs.put(9,  "Delete the user with id/login     -> DELETE method idOrLogin");
        this.commandsExs.put(10, "Quit the program                  -> QUIT");
        this.commandsExs.put(11, "----- Available commands -----");
    }

    private void displayCommandsExamples()
    {
        this.commandsExs.values().forEach(System.out::println);
    }

    public void launch()
    {
        String command = "";
        while (!command.trim().equalsIgnoreCase("QUIT"))
        {
            System.out.print("Command -> ");
            command = scanner.nextLine();
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

                case "GETALL": this.processGetAllCommand(); break;
                case "GETBYID": this.processGetByIdCommand(params); break;
                case "GETBYLOGIN": this.processGetByLoginCommand(params); break;
                case "GETBYNAME": this.processGetByNameCommand(params); break;
                case "GETBYPAYMENTMETHOD": this.processGetByPaymentCommand(params); break;

                case "UPDATEPASSWORD" : this.processUpdatePasswordCommand(params); break;
                case "UPDATENAME" : this.processUpdateNameCommand(params); break;

                case "DELETE" : this.processDeleteCommand(params); break;

                case "QUIT": System.out.println("See ya !"); break;

                default: System.out.println("Unrecognized command [" + params[0].toUpperCase() + "]"); break;
            }
        } catch (NoUserFound | FailedToUpdateUser | FailedToCreateUser e) {
            e.printStackTrace();
        }

    }

    private void processCreateCommand(String[] params) throws FailedToCreateUser
    {
        if (params.length != 9)
        {
            System.out.println(this.commandsExs.get(1));
            return;
        }

        this.userController.create(
                params[1], params[2], params[3], params[4].toUpperCase(),
                params[5], params[6].toLowerCase(), params[7], Integer.parseInt(params[8])
        );
    }

    private void processGetAllCommand() throws NoUserFound
    {
        this.userController.getAll().forEach(System.out::println);
    }

    private void processGetByIdCommand(String[] params) throws NoUserFound
    {
        if (params.length != 2)
        {
            System.out.println(this.commandsExs.get(3));
            return;
        }

        System.out.println(this.userController.getById(params[1].toLowerCase()));
    }

    private void processGetByLoginCommand(String[] params) throws NoUserFound
    {
        if (params.length != 2)
        {
            System.out.println(this.commandsExs.get(4));
            return;
        }

        System.out.println(this.userController.getByLogin(params[1]));
    }

    private void processGetByNameCommand(String[] params) throws NoUserFound
    {
        if (params.length != 2)
        {
            System.out.println(this.commandsExs.get(5));
            return;
        }

        this.userController.getByName(params[1]).forEach(System.out::println);
    }

    private void processGetByPaymentCommand(String[] params) throws NoUserFound
    {
        if (params.length != 2)
        {
            System.out.println(this.commandsExs.get(6));
            return;
        }

        this.userController.getByPaymentMethod(params[1].toUpperCase()).forEach(System.out::println);
    }

    private void processUpdatePasswordCommand(String[] params) throws NoUserFound, FailedToUpdateUser
    {
        if (params.length != 4)
        {
            System.out.println(this.commandsExs.get(7));
            return;
        }

        this.userController.updatePasswordBy(params[1].toUpperCase(), params[2], params[3]);
    }

    private void processUpdateNameCommand(String[] params) throws NoUserFound, FailedToUpdateUser
    {
        if (params.length != 4)
        {
            System.out.println(this.commandsExs.get(8));
            return;
        }

        this.userController.updateNameBy(params[1].toUpperCase(), params[2], params[3]);
    }

    private void processDeleteCommand(String[] params) throws NoUserFound, FailedToUpdateUser
    {
        if (params.length != 3)
        {
            System.out.println(this.commandsExs.get(9));
            return;
        }

        this.userController.deleteBy(params[1].toUpperCase(), params[2]);
    }
}
