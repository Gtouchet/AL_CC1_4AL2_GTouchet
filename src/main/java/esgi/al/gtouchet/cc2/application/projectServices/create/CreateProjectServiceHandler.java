package esgi.al.gtouchet.cc2.application.projectServices.create;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.builders.ProjectBuilder;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.valueObjects.Date;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

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
