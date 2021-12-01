package esgi.al.cc1.application;

import esgi.al.cc1.domain.valueObjects.Id;

public interface ProjectService
{
    Id create(Id contractorId, int department);
    void read();
    void read(Id id);
    void update(Id id, Id contractorId, int department);
    void delete(Id id);

    void engageWorker(Id projectId, Id workerId);
    void fireWorker(Id projectId, Id workerId);
}
