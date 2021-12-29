package al.cc2.gtouchet.builders;

import al.cc2.gtouchet.domain.builders.ProjectBuilder;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProjectBuilderTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void buildProject()
    {
        EntityId projectId = EntityId.generate();
        EntityId contractorId = EntityId.generate();
        List<EntityId> workersId = Arrays.asList(EntityId.generate(), EntityId.generate(), EntityId.generate());

        Project project = ProjectBuilder.init(projectId, Clock.now())
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
        Project project = ProjectBuilder.init(EntityId.generate(), Clock.now())
                .setContractorId(EntityId.generate())
                // Department set to Integer's default if not specified (0)
                .setWorkersId(new ArrayList<>())
                .build();

        assertNotNull(project);

        assertEquals(0, project.getDepartment());
    }

    @Test
    public void buildProject_withoutWorkers()
    {
        Project project = ProjectBuilder.init(EntityId.generate(), Clock.now())
                .setContractorId(EntityId.generate())
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

        ProjectBuilder.init(EntityId.generate(), Clock.now())
                //.setContractorId(Id.generate())
                .setDepartment(75)
                .setWorkersId(new ArrayList<>())
                .build();
    }
}
