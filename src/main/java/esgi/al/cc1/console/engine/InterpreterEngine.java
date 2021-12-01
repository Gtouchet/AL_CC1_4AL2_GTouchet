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

        this.displayCommandsList();
    }

    private void displayCommandsList()
    {
        System.out.println("---Available commands---");
        Command.getCommands().forEach(command -> {
            if (command.usage != null) System.out.println(command.usage);
        });
        System.out.println("---Command input---");
    }

    public void run()
    {
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
