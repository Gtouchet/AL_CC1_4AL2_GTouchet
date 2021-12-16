package esgi.al.gtouchet.cc2.application.services.project;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

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
