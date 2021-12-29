package al.cc2.gtouchet.console.handlers.worker;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.worker.CreateWorkerCommand;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.user.WorkerService;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.domain.valueObjects.Password;

import java.util.Objects;

public final class CreateWorkerConsoleHandler implements ConsoleHandler
{
    private final CommandHandler<Worker, CreateWorkerCommand> commandHandler;

    public CreateWorkerConsoleHandler(CommandHandler commandHandler) throws NullPointerException
    {
        this.commandHandler = Objects.requireNonNull(commandHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.CREATE_WORKER.parameters)
        {
            try {
                Worker worker = this.commandHandler.handle(new CreateWorkerCommand(
                        params[1],
                        Password.of(params[2]),
                        params[3],
                        WorkerService.valueOf(params[4].toUpperCase()),
                        Integer.parseInt(params[5])
                ));
                if (worker != null)
                {
                    System.out.println(worker);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: could not parse [" + params[5] + "] as a department");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: unknown service [" + params[4] + "]");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(ConsoleCommand.CREATE_WORKER);
        }
    }
}
