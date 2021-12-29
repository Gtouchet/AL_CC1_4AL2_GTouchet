package al.cc2.gtouchet.application.services.handlers.project;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.project.UpdateProjectCommand;
import al.cc2.gtouchet.domain.builders.ProjectBuilder;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public final class UpdateProjectCommandHandler implements CommandHandler<Project, UpdateProjectCommand>
{
    private final Repository<Project> projectRepository;
    private final Repository<Contractor> contractorRepository;

    public UpdateProjectCommandHandler(
            Repository projectRepository,
            Repository contractorRepository)
    {
        this.projectRepository = projectRepository;
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Project handle(UpdateProjectCommand command)
    {
        try {
            Project project = this.projectRepository.read(command.id);

            if(!this.contractorRepository.exists(command.contractorId))
            {
                System.out.println("Error: no Contractor registered with ID [" + command.contractorId + "]");
                return null;
            }

            project = ProjectBuilder.init(project)
                    .setContractorId(command.contractorId)
                    .setDepartment(command.department)
                    .build();

            this.projectRepository.update(command.id, project);

            return project;

        } catch (EntityNotFoundException | NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
