package esgi.al.gtouchet.cc2;

import esgi.al.gtouchet.cc2.application.services.factories.DataServicesFactory;
import esgi.al.gtouchet.cc2.application.services.factories.ServicesFactory;
import esgi.al.gtouchet.cc2.console.engine.InterpreterEngine;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.DataRepositoriesFactory;
import esgi.al.gtouchet.cc2.web.ApiEngine;

public class App
{
    public static void main(String[] args)
    {
        ServicesFactory servicesFactory = new DataServicesFactory(new DataRepositoriesFactory());

        // Starts the console application thread
        new InterpreterEngine(servicesFactory).start();

        // Starts the API thread
        // Todo: implements quarkus
        new ApiEngine().start();
    }
}
