package al.cc2.gtouchet.application.services.worker.dtos;

import al.cc2.gtouchet.domain.models.Service;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.domain.valueObjects.Password;

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
