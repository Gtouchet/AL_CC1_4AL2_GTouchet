package esgi.al.gtouchet.cc2;

import esgi.al.gtouchet.cc2.application.ServicesFactory;
import esgi.al.gtouchet.cc2.console.engine.InterpreterEngine;
import esgi.al.gtouchet.cc2.web.ApiEngine;

public class App
{
    public static void main(String[] args)
    {
        // Starts the console application thread
        new InterpreterEngine(new ServicesFactory()).start();

        // Starts the API thread
        // Todo: implements quarkus
        new ApiEngine().start();
    }
}
