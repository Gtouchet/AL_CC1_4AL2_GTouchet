package esgi.al.utils;

import esgi.al.controllers.UserController;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.exceptions.NoUserFound;

import java.util.Scanner;

public class CommandInputHandler
{
    private final UserController userController;
    private final Scanner scanner;

    public CommandInputHandler(UserController userController)
    {
        this.userController = userController;
        this.scanner = new Scanner(System.in);
    }

    public void launch()
    {
        this.displayCommandList();

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
                case "GETALL": this.processGetAllCommand(); break;
                case "GETBYID": this.processGetByIdCommand(params); break;
                case "GETBYLOGIN": this.processGetByLoginCommand(params); break;
                case "GETBYNAME": this.processGetByNameCommand(params); break;
                case "GETBYPAYMENTMETHOD": this.processGetByPaymentMethodCommand(params); break;

                case "UPDATEPASSWORD" : this.processUpdatePasswordCommand(params); break;
                case "UPDATENAME" : this.processUpdateNameCommand(params); break;

                case "DELETE" : this.processDeleteCommand(params); break;

                case "QUIT": System.out.println("See ya !"); break;

                default: System.out.println("Unrecognized command [" + params[0].toUpperCase() + "]"); break;
            }
        } catch (NoUserFound | FailedToUpdateUser e) {
            e.printStackTrace();
        }

    }

    private void processGetAllCommand() throws NoUserFound
    {
        this.userController.getAll().forEach(System.out::println);
    }

    private void processGetByIdCommand(String[] params) throws NoUserFound
    {
        if (params.length != 2)
        {
            System.out.println(
                    "Wrong number of parameters for GETBYID command\n" +
                    "Example: GETBYID 9ccad235-0eb6-40d1-b5d6-c0e93e919ad8"
            );
            return;
        }

        System.out.println(this.userController.getById(params[1].toLowerCase()));
    }

    private void processGetByLoginCommand(String[] params) throws NoUserFound
    {
        if (params.length != 2)
        {
            System.out.println(
                    "Wrong number of parameters for GETBYLOGIN command\n" +
                    "Example: GETBYLOGIN userLogin"
            );
            return;
        }

        System.out.println(this.userController.getByLogin(params[1]));
    }

    private void processGetByNameCommand(String[] params) throws NoUserFound
    {
        if (params.length != 2)
        {
            System.out.println(
                    "Wrong number of parameters for GETBYNAME command\n" +
                    "Example: GETBYNAME userName"
            );
            return;
        }

        this.userController.getByName(params[1]).forEach(System.out::println);
    }

    private void processGetByPaymentMethodCommand(String[] params) throws NoUserFound
    {
        if (params.length != 2)
        {
            System.out.println(
                    "Wrong number of parameters for GETBYPAYMENTMETHOD command\n" +
                    "Example: GETBYPAYMENTMETHOD card"
            );
            return;
        }

        this.userController.getByPaymentMethod(params[1].toUpperCase()).forEach(System.out::println);
    }

    private void processUpdatePasswordCommand(String[] params) throws NoUserFound, FailedToUpdateUser
    {
        if (params.length != 4)
        {
            System.out.println(
                    "Wrong number of parameters for UPDATEPASSWORD command\n" +
                    "Example: UPDATEPASSWORD login userLogin newPassword"
            );
            return;
        }

        this.userController.updatePasswordBy(params[1].toUpperCase(), params[2], params[3]);
    }

    private void processUpdateNameCommand(String[] params) throws NoUserFound, FailedToUpdateUser
    {
        if (params.length != 4)
        {
            System.out.println(
                    "Wrong number of parameters for UPDATENAME command\n" +
                    "Example: UPDATENAME login userLogin newName"
            );
            return;
        }

        this.userController.updateNameBy(params[1].toUpperCase(), params[2], params[3]);
    }

    private void processDeleteCommand(String[] params) throws NoUserFound, FailedToUpdateUser
    {
        if (params.length != 3)
        {
            System.out.println(
                    "Wrong number of parameters for DELETE command\n" +
                    "Example: DELETE login userLogin"
            );
            return;
        }

        this.userController.deleteBy(params[1].toUpperCase(), params[2]);
    }

    private void displayCommandList()
    {
        System.out.println(
                "----- ----- Available commands ----- -----\n" +
                "GETALL -> get all users\n" +
                "GETBYID ID -> get the user registered under the specified ID\n" +
                "GETBYLOGIN login -> same with login\n" +
                "GETBYNAME name -> get all users registered with the specified name\n" +
                "GETBYPAYMENTMETHOD paymentMethod -> same with payment method\n\n" +
                // todo "CREATE login password name paymentMethod city streetType streetName streetNumber -> create a new user\n\n" +
                "UPDATEPASSWORD method idOrLogin newPassword -> update the user's password registered under the specified id or login\n" +
                "UPDATENAME method idOrLogin newName -> same with name\n\n" +
                "DELETE method idOrLogin -> delete the user registered under the specified id or login\n\n" +
                "QUIT -> quit the program\n" +
                "----- ----- ----- ----- ----- ----- -----"
        );
    }
}
