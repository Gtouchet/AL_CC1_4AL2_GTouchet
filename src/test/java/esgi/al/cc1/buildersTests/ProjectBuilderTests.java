package esgi.al.cc1.buildersTests;

import esgi.al.cc1.domain.builders.ProjectBuilder;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectBuilderTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void buildProject()
    {
        Id projectId = Id.generate();
        Id contractorId = Id.generate();
        List<Id> workersId = Arrays.asList(Id.generate(), Id.generate(), Id.generate());

        Project project = ProjectBuilder.init(projectId, Date.now())
                .setContractorId(contractorId)
                .setDepartment(75)
                .setWorkersId(workersId)
                .build();

        assertNotNull(project);

        assertEquals(projectId, project.getId());
        assertEquals(75, project.getDepartment());
        assertEquals(workersId, project.getWorkersId());
    }

    @Test
    public void buildProject_withoutDepartment()
    {
        Project project = ProjectBuilder.init(Id.generate(), Date.now())
                .setContractorId(Id.generate())
                // Department set to Integer's default if not specified (0)
                .setWorkersId(new ArrayList<>())
                .build();

        assertNotNull(project);

        assertEquals(0, project.getDepartment());
    }

    @Test
    public void buildProject_withoutWorkers()
    {
        Project project = ProjectBuilder.init(Id.generate(), Date.now())
                .setContractorId(Id.generate())
                .setDepartment(75)
                // Workers list set to empty list of not specified
                .build();

        assertNotNull(project);

        assertEquals(0, project.getWorkersId().size());
    }

    @Test
    public void buildProject_missingOneProperty()
    {
        exception.expect(NullPointerException.class);

        ProjectBuilder.init(Id.generate(), Date.now())
                //.setContractorId(Id.generate())
                .setDepartment(75)
                .setWorkersId(new ArrayList<>())
                .build();
    }
}
