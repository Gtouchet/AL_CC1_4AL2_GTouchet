package esgi.al.gtouchet.cc2.application.projectService;

import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public interface ProjectService
{
    Id create(Id contractorId, int department);
    void read();
    void read(Id id);
    void update(Id id, Id contractorId, int department);
    void delete(Id id);

    long getRepositorySize();
    boolean exists(Id id);

    void engageWorker(Id projectId, Id workerId);
    void fireWorker(Id projectId, Id workerId);
}
