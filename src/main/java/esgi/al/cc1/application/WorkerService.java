package esgi.al.cc1.application;

import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

public interface WorkerService
{
    Id create(String login, Password password, String name, Service service, int department);
    void read();
    void read(Id id);
    void update(Id id, Password password, String name, Service service, int department);
    void delete(Id id);

    long getRepositorySize();
    boolean exists(Id id);
}
