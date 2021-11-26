package esgi.al.cc1.console;

import esgi.al.cc1.console.enumerators.CommandKeyword;
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
        CommandKeyword.getCommandKeywords().forEach(command -> System.out.println(command.usageExample));
        System.out.println("---Command input---");
    }

    public void run()
    {
        String command = "";
        while (!command.split(" ")[0].equalsIgnoreCase(CommandKeyword.QUIT.toString()))
        {
            System.out.print("> ");
            command = this.scanner.nextLine().trim();
            new CommandProcessor(this.controllersFactory).process(command);
        }
    }
}
