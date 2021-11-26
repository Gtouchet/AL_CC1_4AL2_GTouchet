package esgi.al.cc1.application.console;

import esgi.al.cc1.factories.ControllersFactory;

import java.util.Scanner;

public class InterpreterEngine extends Thread
{
    private final ControllersFactory controllersFactory;

    private final Scanner scanner;

    public InterpreterEngine(ControllersFactory controllersFactory)
    {
        this.controllersFactory = controllersFactory;

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
            new CommandProcessor(this.controllersFactory).process(command);
        }
    }
}
