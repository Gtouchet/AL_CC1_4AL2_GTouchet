package al.cc2.gtouchet.console.commandHandlers.worker;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.worker.dtos.CreateWorkerDto;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.models.Service;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.valueObjects.Password;

import java.util.Objects;

public class CreateWorkerHandler implements CommandHandler
{
    private final ServiceHandler<Worker, CreateWorkerDto> serviceHandler;

    public CreateWorkerHandler(ServiceHandler<Worker, CreateWorkerDto> serviceHandler) throws NullPointerException
    {
        this.serviceHandler = Objects.requireNonNull(serviceHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.CREATE_WORKER.parameters)
        {
            try {
                Worker worker = this.serviceHandler.handle(new CreateWorkerDto(
                        params[1],
                        Password.of(params[2]),
                        params[3],
                        Service.valueOf(params[4].toLowerCase()),
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
            throw new WrongNumberOfArgumentException(Command.CREATE_WORKER);
        }
    }
}
