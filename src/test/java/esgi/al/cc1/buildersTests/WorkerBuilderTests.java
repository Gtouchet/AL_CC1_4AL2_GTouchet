package esgi.al.cc1.buildersTests;

import esgi.al.cc1.domain.builders.WorkerBuilder;
import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class WorkerBuilderTests
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
                .setService(Service.builder)
                .setDepartment(91)
                .build();

        assertNotNull(worker);

        assertEquals(id, worker.getId());
        assertEquals("ABcd1234!", worker.getPassword().toString());
        assertEquals("Guillaume", worker.getName());
        assertEquals(Service.builder, worker.getService());
        assertEquals(91, worker.getDepartment());
    }

    @Test
    public void buildWorker_withoutDepartment()
    {
        Worker worker = WorkerBuilder.init(Id.generate(), "GTouchet", Date.now())
                .setPassword(Password.of("ABcd1234!"))
                .setName("Guillaume")
                .setService(Service.builder)
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
