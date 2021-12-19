package al.cc2.gtouchet.application.services.project;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

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
