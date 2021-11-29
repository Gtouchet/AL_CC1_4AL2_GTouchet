package esgi.al.cc1.application.commandHandlers.contractor;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.enumerators.Command;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.infrastructure.controllers.Controller;

import java.util.List;
import java.util.stream.Collectors;

public class ReadContractorHandler implements CommandHandler
{
    private final Controller<Contractor> contractorController;

    public ReadContractorHandler(Controller<Contractor> contractorController)
    {
        this.contractorController = contractorController;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgument
    {
        if (params.length == Command.readContractor.parameters)
        {
            List<Contractor> contractors = this.contractorController.read().collect(Collectors.toUnmodifiableList());
            if (contractors.size() == 0)
            {
                System.out.println("No contractor registered yet");
            }
            else
            {
                contractors.forEach(System.out::println);
            }
        }
        else if (params.length == Command.readContractor.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            System.out.println(this.contractorController.read(params[1]));
        }
        else
        {
            throw new WrongNumberOfArgument(Command.readContractor);
        }
    }
}
