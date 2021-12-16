package esgi.al.gtouchet.cc2.application.services.project.delete;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

public class DeleteProjectServiceHandler implements ServiceHandler<Boolean, Id>
{
    private final Repository<Project> projectRepository;

    public DeleteProjectServiceHandler(Repository<Project> projectRepository)
    {
        this.projectRepository = projectRepository;
    }

    @Override
    public Boolean handle(Id command)
    {
        try {
            this.projectRepository.remove(command);
            return true;
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
