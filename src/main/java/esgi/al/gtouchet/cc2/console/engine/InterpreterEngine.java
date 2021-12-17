package esgi.al.gtouchet.cc2.console.engine;

import java.util.Scanner;

public class InterpreterEngine extends Thread
{
    private final CommandProcessor commandProcessor;
    private final Scanner scanner;

    public InterpreterEngine(CommandProcessor commandProcessor)
    {
        this.commandProcessor = commandProcessor;
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
            this.commandProcessor.process(command);
        }

        System.out.println("See ya !");
    }
}
