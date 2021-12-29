package al.cc2.gtouchet.application.services.handlers.project;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.project.ReadProjectQuery;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public final class ReadProjectQueryHandler implements QueryHandler<Project, ReadProjectQuery>
{
    private final Repository<Project> projectRepository;

    public ReadProjectQueryHandler(Repository projectRepository)
    {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project handle(ReadProjectQuery query)
    {
        try {
            return this.projectRepository.read(query.id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
