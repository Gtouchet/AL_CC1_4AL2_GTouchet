package esgi.al.cc1.application.commandHandlers.project;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.enumerators.Command;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.infrastructure.controllers.Controller;

import java.util.List;
import java.util.stream.Collectors;

public class ReadProjectHandler implements CommandHandler
{
    private final Controller<Project> projectController;

    public ReadProjectHandler(Controller<Project> projectController)
    {
        this.projectController = projectController;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgument
    {
        if (params.length == Command.readProject.parameters)
        {
            List<Project> projects = this.projectController.read().collect(Collectors.toUnmodifiableList());
            if (projects.size() == 0)
            {
                System.out.println("No project registered yet");
            }
            else
            {
                projects.forEach(System.out::println);
            }
        }
        else if (params.length == Command.readProject.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            System.out.println(this.projectController.read(params[1]));
        }
        else
        {
            throw new WrongNumberOfArgument(Command.readProject);
        }
    }
}
