package al.cc2.gtouchet.builders;

import al.cc2.gtouchet.domain.builders.WorkerBuilder;
import al.cc2.gtouchet.domain.models.user.WorkerService;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.domain.valueObjects.Password;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WorkerBuilderTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void buildWorker()
    {
        Id id = Id.generate();

        Worker worker = WorkerBuilder.init(id, "GTouchet", Date.now())
                .setPassword(Password.of("ABcd1234!"))
                .setName("Guillaume")
                .setService(WorkerService.BUILDER)
                .setDepartment(91)
                .build();

        assertNotNull(worker);

        assertEquals(id, worker.getId());
        assertEquals("ABcd1234!", worker.getCredentials().getPassword().toString());
        assertEquals("Guillaume", worker.getName());
        assertEquals(WorkerService.BUILDER, worker.getService());
        assertEquals(91, worker.getDepartment());
    }

    @Test
    public void buildWorker_withoutDepartment()
    {
        Worker worker = WorkerBuilder.init(Id.generate(), "GTouchet", Date.now())
                .setPassword(Password.of("ABcd1234!"))
                .setName("Guillaume")
                .setService(WorkerService.BUILDER)
                // Department set to Integer's default if not specified (0)
                .build();

        assertNotNull(worker);

        assertEquals(0, worker.getDepartment());
    }

    @Test
    public void buildWorker_missingOneProperty()
    {
        exception.expect(NullPointerException.class);

        WorkerBuilder.init(Id.generate(), "GTouchet", Date.now())
                .setPassword(Password.of("ABcd1234!"))
                .setName("Guillaume")
                //.setService(Service.builder)
                .setDepartment(91)
                .build();
    }
}
