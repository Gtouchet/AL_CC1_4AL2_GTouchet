package al.cc2.gtouchet.application.services.handlers.project;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.project.CreateProjectCommand;
import al.cc2.gtouchet.domain.builders.ProjectBuilder;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public final class CreateProjectCommandHandler implements CommandHandler<Project, CreateProjectCommand>
{
    private final Repository<Project> projectRepository;
    private final Repository<Contractor> contractorRepository;

    public CreateProjectCommandHandler(
            Repository projectRepository,
            Repository contractorRepository)
    {
        this.projectRepository = projectRepository;
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Project handle(CreateProjectCommand command)
    {
        if(!this.contractorRepository.exists(command.contractorId))
        {
            System.out.println("Error: no Contractor registered with ID [" + command.contractorId + "]");
            return null;
        }

        try {
            Project project = ProjectBuilder.init(Id.generate(), Date.now())
                    .setContractorId(command.contractorId)
                    .setDepartment(command.department)
                    .build();

            this.projectRepository.create(project);

            return project;

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
