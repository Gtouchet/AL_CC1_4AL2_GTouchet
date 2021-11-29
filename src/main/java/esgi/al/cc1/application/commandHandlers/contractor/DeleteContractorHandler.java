package esgi.al.cc1.application.commandHandlers.contractor;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.enumerators.Command;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.infrastructure.controllers.Controller;

public class DeleteContractorHandler implements CommandHandler
{
    private final Controller<Contractor> contractorController;

    public DeleteContractorHandler(Controller<Contractor> contractorController)
    {
        this.contractorController = contractorController;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgument
    {
        if (params.length == Command.deleteContractor.parameters)
        {
            this.contractorController.remove(params[1]);
        }
        else
        {
            throw new WrongNumberOfArgument(Command.deleteContractor);
        }
    }
}
