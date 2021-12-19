package al.cc2.gtouchet.console.commandHandlers.worker;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.worker.dtos.UpdateWorkerDto;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.models.Service;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.domain.valueObjects.Password;

import java.util.Objects;

public class UpdateWorkerHandler implements CommandHandler
{
    private final ServiceHandler<Worker, UpdateWorkerDto> serviceHandler;

    public UpdateWorkerHandler(ServiceHandler<Worker, UpdateWorkerDto> serviceHandler) throws NullPointerException
    {
        this.serviceHandler = Objects.requireNonNull(serviceHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.UPDATE_WORKER.parameters)
        {
            try {
                Worker worker = this.serviceHandler.handle(new UpdateWorkerDto(
                        Id.fromString(params[1].toLowerCase()),
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
            throw new WrongNumberOfArgumentException(Command.UPDATE_WORKER);
        }
    }
}
