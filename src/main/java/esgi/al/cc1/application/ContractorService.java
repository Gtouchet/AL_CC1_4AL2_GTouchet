package esgi.al.cc1.application;

import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

public interface ContractorService
{
    Id create(String login, Password password, String name, PaymentMethod paymentMethod);
    void read();
    void read(Id id);
    void update(Id id, Password password, String name, PaymentMethod paymentMethod);
    void delete(Id id);

    long getRepositorySize();
    boolean exists(Id id);
    void validatePayment(Id id);
}
