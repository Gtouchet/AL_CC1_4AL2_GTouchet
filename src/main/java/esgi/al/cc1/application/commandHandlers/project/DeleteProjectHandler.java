package esgi.al.cc1.application.commandHandlers.project;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.enumerators.Command;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.infrastructure.controllers.Controller;

public class DeleteProjectHandler implements CommandHandler
{
    private final Controller<Project> projectController;

    public DeleteProjectHandler(Controller<Project> projectController)
    {
        this.projectController = projectController;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgument
    {
        if (params.length == Command.deleteProject.parameters)
        {
            this.projectController.remove(params[1]);
        }
        else
        {
            throw new WrongNumberOfArgument(Command.deleteProject);
        }
    }
}
