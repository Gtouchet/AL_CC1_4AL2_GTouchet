package esgi.al.gtouchet.cc2.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Services
{
    private final List<ServiceHandler> services;

    public Services()
    {
        this.services = new ArrayList<>();
    }

    public void register(ServiceHandler service)
    {
        Objects.requireNonNull(service);
        if (!this.services.contains(service))
        {
            this.services.add(service);
        }
    }

    public ServiceHandler retrieve(Class/*<ServiceHandler>*/ service)
    {
        return this.services.stream()
                .filter(registeredService -> registeredService.getClass().equals(service))
                .findFirst()
                .orElse(null);
    }
}
