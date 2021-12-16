package esgi.al.gtouchet.cc2.console.commandHandlers.worker;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.update.UpdateWorkerDto;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Service;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;

public class UpdateWorkerHandler implements CommandHandler
{
    private final ServiceHandler<Worker, UpdateWorkerDto> serviceHandler;

    public UpdateWorkerHandler(ServiceHandler<Worker, UpdateWorkerDto> serviceHandler)
    {
        this.serviceHandler = serviceHandler;
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
