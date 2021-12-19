package al.cc2.gtouchet.application.services.project;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ReadAllProjectServiceHandler implements ServiceHandler<List<Project>, Void>
{
    private final Repository<Project> projectRepository;

    public ReadAllProjectServiceHandler(Repository<Project> projectRepository)
    {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> handle(Void command)
    {
        return this.projectRepository.read().collect(Collectors.toList());
    }
}
