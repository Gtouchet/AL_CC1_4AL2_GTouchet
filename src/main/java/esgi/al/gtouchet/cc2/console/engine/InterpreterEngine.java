package esgi.al.gtouchet.cc2.console.engine;

import esgi.al.gtouchet.cc2.application.services.factories.ServicesFactory;

import java.util.Scanner;

public class InterpreterEngine extends Thread
{
    private final ServicesFactory servicesFactory;
    private final Scanner scanner;

    public InterpreterEngine(ServicesFactory servicesFactory)
    {
        this.servicesFactory = servicesFactory;
        this.scanner = new Scanner(System.in);
    }

    public void run()
    {
        System.out.println("\nConsole application, type 'help' to see the list of commands");

        String command = "";
        while (!command.trim().split(" ")[0].equalsIgnoreCase(Command.QUIT.keyword))
        {
            System.out.print("> ");
            command = this.scanner.nextLine();
            new CommandProcessor(this.servicesFactory).process(command);
        }

        System.out.println("See ya !");
    }
}
