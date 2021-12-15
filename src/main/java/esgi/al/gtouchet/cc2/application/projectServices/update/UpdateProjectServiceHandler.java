package esgi.al.gtouchet.cc2.application.projectServices.update;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.builders.ProjectBuilder;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

public class UpdateProjectServiceHandler implements ServiceHandler<Project, UpdateProjectDto>
{
    private final Repository<Project> projectRepository;
    private final Repository<Contractor> contractorRepository;

    public UpdateProjectServiceHandler(
            Repository<Project> projectRepository,
            Repository<Contractor> contractorRepository)
    {
        this.projectRepository = projectRepository;
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Project handle(UpdateProjectDto command)
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
