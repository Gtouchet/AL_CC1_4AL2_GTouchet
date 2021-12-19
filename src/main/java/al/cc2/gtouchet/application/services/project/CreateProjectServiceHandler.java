package al.cc2.gtouchet.application.services.project;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.project.dtos.CreateProjectDto;
import al.cc2.gtouchet.domain.builders.ProjectBuilder;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class CreateProjectServiceHandler implements ServiceHandler<Project, CreateProjectDto>
{
    private final Repository<Project> projectRepository;
    private final Repository<Contractor> contractorRepository;

    public CreateProjectServiceHandler(
            Repository<Project> projectRepository,
            Repository<Contractor> contractorRepository)
    {
        this.projectRepository = projectRepository;
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Project handle(CreateProjectDto command)
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
