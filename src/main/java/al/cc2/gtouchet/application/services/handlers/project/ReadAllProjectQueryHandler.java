package al.cc2.gtouchet.application.services.handlers.project;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.project.ReadAllProjectQuery;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ReadAllProjectQueryHandler implements QueryHandler<List<Project>, ReadAllProjectQuery>
{
    private final Repository<Project> projectRepository;

    public ReadAllProjectQueryHandler(Repository<Project> projectRepository)
    {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> handle(ReadAllProjectQuery query)
    {
        return this.projectRepository.read().collect(Collectors.toList());
    }
}
