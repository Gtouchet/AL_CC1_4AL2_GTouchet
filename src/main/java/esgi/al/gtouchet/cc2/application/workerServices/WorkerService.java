package esgi.al.gtouchet.cc2.application.workerServices;

import esgi.al.gtouchet.cc2.domain.models.Service;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;

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
