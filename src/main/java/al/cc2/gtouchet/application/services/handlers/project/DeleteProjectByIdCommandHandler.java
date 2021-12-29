package al.cc2.gtouchet.application.services.handlers.project;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.project.DeleteProjectCommand;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public final class DeleteProjectByIdCommandHandler implements CommandHandler<Boolean, DeleteProjectCommand>
{
    private final Repository<Project> projectRepository;

    public DeleteProjectByIdCommandHandler(Repository projectRepository)
    {
        this.projectRepository = projectRepository;
    }

    @Override
    public Boolean handle(DeleteProjectCommand command)
    {
        try {
            this.projectRepository.remove(command.id);
            return true;
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
