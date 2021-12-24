package al.cc2.gtouchet.console.engine;

import java.util.Scanner;

public class InterpreterEngine
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
        while (!command.trim().split(" ")[0].equalsIgnoreCase(ConsoleCommand.QUIT.keyword))
        {
            System.out.print("> ");
            command = this.scanner.nextLine();
            this.commandProcessor.process(command);
        }

        System.out.println("See ya !");
    }
}
