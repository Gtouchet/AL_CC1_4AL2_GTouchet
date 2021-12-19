package al.cc2.gtouchet.application.services.project;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

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
