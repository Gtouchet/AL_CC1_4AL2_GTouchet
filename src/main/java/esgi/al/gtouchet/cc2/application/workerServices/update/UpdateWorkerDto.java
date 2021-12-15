package esgi.al.gtouchet.cc2.application.workerServices.update;

import esgi.al.gtouchet.cc2.domain.models.Service;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;

public class UpdateWorkerDto
{
    public final Id id;
    public final Password password;
    public final String name;
    public final Service service;
    public final int department;

    public UpdateWorkerDto(
            Id id,
            Password password,
            String name,
            Service service,
            int department)
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.service = service;
        this.department = department;
    }
}
