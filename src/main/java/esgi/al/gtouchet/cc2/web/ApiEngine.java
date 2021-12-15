package esgi.al.gtouchet.cc2.web;

import esgi.al.gtouchet.cc2.application.ServicesFactory;

public class ApiEngine extends Thread
{
    private final ServicesFactory servicesFactory;

    public ApiEngine(ServicesFactory servicesFactory)
    {
        this.servicesFactory = servicesFactory;
    }

    public void run()
    {

    }
}
