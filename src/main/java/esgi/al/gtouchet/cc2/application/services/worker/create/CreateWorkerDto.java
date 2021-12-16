package esgi.al.gtouchet.cc2.application.services.worker.create;

import esgi.al.gtouchet.cc2.domain.models.Service;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;

public class CreateWorkerDto
{
    public final String login;
    public final Password password;
    public final String name;
    public final Service service;
    public final int department;

    public CreateWorkerDto(
            String login,
            Password password,
            String name,
            Service service,
            int department)
    {
        this.login = login;
        this.password = password;
        this.name = name;
        this.service = service;
        this.department = department;
    }
}
