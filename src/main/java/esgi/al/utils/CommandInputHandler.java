package esgi.al.utils;

import esgi.al.controllers.UserController;
import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.exceptions.NoUserFound;

import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

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
        while (!command.trim().toUpperCase(Locale.ROOT).equals("QUIT"))
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

            switch (params[0].toUpperCase(Locale.ROOT))
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

                default: System.out.println("Unrecognized command [" + params[0].toUpperCase(Locale.ROOT) + "]"); break;
            }
        } catch (NoUserFound | FailedToUpdateUser e) {
            e.printStackTrace();
        }

    }

    private void processGetAllCommand() throws NoUserFound
    {
        this.userController.getAll()
                .forEach(System.out::println);
    }

    private void processGetByIdCommand(String[] params) throws NoUserFound
    {
        if (params.length <= 1)
        {
            System.out.println("Please indicate an ID to look for");
            return;
        }

        try {
            UUID uuid = UUID.fromString(params[1]);
            System.out.println(this.userController.getById(uuid));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void processGetByLoginCommand(String[] params) throws NoUserFound
    {
        if (params.length <= 1)
        {
            System.out.println("Please indicate a login to look for");
            return;
        }

        System.out.println(this.userController.getByLogin(params[1]));
    }

    private void processGetByNameCommand(String[] params) throws NoUserFound
    {
        if (params.length <= 1)
        {
            System.out.println("Please indicate a name to look for");
            return;
        }

        this.userController.getByName(params[1])
                .forEach(System.out::println);
    }

    private void processGetByPaymentMethodCommand(String[] params) throws NoUserFound
    {
        if (params.length <= 1)
        {
            System.out.println("Please indicate a payment method ('card', 'paypal' or 'unspecified') to look for");
            return;
        }

        String paymentMethod = params[1].toUpperCase(Locale.ROOT);
        if (!paymentMethod.equals(PaymentMethod.CARD.toString()) &&
                !paymentMethod.equals(PaymentMethod.PAYPAL.toString()) &&
                !paymentMethod.equals(PaymentMethod.UNSPECIFIED.toString()))
        {
            System.out.println("Unknown payment method [" + paymentMethod + "], it should be 'card', 'paypal' or 'unspecified'");
            return;
        }
        this.userController.getByPaymentMethod(PaymentMethod.valueOf(paymentMethod))
                .forEach(System.out::println);
    }

    private void processUpdatePasswordCommand(String[] params) throws NoUserFound, FailedToUpdateUser
    {
        if (params.length <= 2)
        {
            System.out.println("Please indicate a user get method ('id' or 'login'), an ID or login and the new password");
            return;
        }

        String getMethod = params[1].toUpperCase(Locale.ROOT);
        if (!getMethod.equals("ID") && !getMethod.equals("LOGIN"))
        {
            System.out.println("Unknown get method [" + getMethod + "], it should be 'id' or 'login'");
            return;
        }

        // Just testing if the specified UUID is valid, does not crash the app if not
        if (getMethod.equals("ID"))
        {
            try {
                UUID uuid = UUID.fromString(params[2]);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return;
            }
        }

        this.userController.updatePasswordBy(getMethod.equals("ID"), params[2], params[3]);
    }

    private void processUpdateNameCommand(String[] params)
    {
        // Todo: implements
    }

    private void processDeleteCommand(String[] params)
    {
        // Todo: implements
    }

    private void displayCommandList()
    {
        System.out.println(
                "----- ----- Available commands ----- -----\n" +
                "GETALL -> get all users\n" +
                "GETBYID ID -> get the user registered under the specified ID\n" +
                "GETBYLOGIN login -> same with login\n" +
                "GETBYNAME name -> get all users registered with the specified name\n" +
                "GETBYPAYMENTMETHOD paymentMethod('card', 'paypal', 'unspecified') -> same with payment method\n\n" +
                "UPDATEPASSWORD method('id' 'login'), idOrLogin, newPassword -> update user's password\n" +
                "UPDATENAME method('id' 'login'), idOrLogin, newName -> update user's name\n\n" +
                "DELETE method('id' 'login'), idOrLogin -> delete the user registered under the specified id or login\n\n" +
                "QUIT -> quit the program\n" +
                "----- ----- ----- ----- ----- ----- -----"
        );
    }
}
