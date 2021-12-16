package esgi.al.gtouchet.cc2.application.services.project.read;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

public class ReadIdProjectServiceHandler implements ServiceHandler<Project, Id>
{
    private final Repository<Project> projectRepository;

    public ReadIdProjectServiceHandler(Repository<Project> projectRepository)
    {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project handle(Id command)
    {
        try {
            return this.projectRepository.read(command);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
