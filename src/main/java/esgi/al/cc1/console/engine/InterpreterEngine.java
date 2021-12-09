package esgi.al.cc1.console.engine;

import esgi.al.cc1.application.ServicesFactory;

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
        System.out.println("\nType 'help' to see the list of commands\n");

        String command = "";
        while (!command.trim().split(" ")[0].equalsIgnoreCase(Command.quit.keyword))
        {
            System.out.print("> ");
            command = this.scanner.nextLine();
            new CommandProcessor(this.servicesFactory).process(command);
        }

        System.out.println("See ya !");
    }
}
