package esgi.al.gtouchet.cc2.web;

import esgi.al.gtouchet.cc2.application.services.ServicesContainer;

public class ApiEngine extends Thread
{
    private final ServicesContainer servicesContainer;

    public ApiEngine(ServicesContainer servicesContainer)
    {
        this.servicesContainer = servicesContainer;
    }

    public void run()
    {

    }
}
