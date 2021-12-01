package esgi.al.cc1.application;

import esgi.al.cc1.domain.valueObjects.Id;

public interface PaymentService
{
    Id create(Id contractorId, Id workerId, double amount, String reason);
    void read();
    void read(Id id);
    // No updating possibility for any registered payment
    void delete(Id id);
}
