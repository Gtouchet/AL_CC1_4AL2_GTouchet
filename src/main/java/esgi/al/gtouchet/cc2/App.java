package esgi.al.gtouchet.cc2;

import esgi.al.gtouchet.cc2.application.services.Services;
import esgi.al.gtouchet.cc2.application.services.ServicesInitializer;
import esgi.al.gtouchet.cc2.console.engine.CommandProcessor;
import esgi.al.gtouchet.cc2.console.engine.InterpreterEngine;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.DataRepositoriesFactory;
import esgi.al.gtouchet.cc2.web.ApiEngine;

public class App
{
    public static void main(String[] args)
    {
        Services services = ServicesInitializer.initialize(new DataRepositoriesFactory());

        // Starts the console application thread
        new InterpreterEngine(new CommandProcessor(services)).start();

        // Starts the API thread
        // Todo: implements quarkus
        new ApiEngine().start();
    }
}
