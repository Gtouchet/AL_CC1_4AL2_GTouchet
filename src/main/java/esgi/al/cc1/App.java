package esgi.al.cc1;

import esgi.al.cc1.application.ServicesFactory;
import esgi.al.cc1.console.engine.InterpreterEngine;
import esgi.al.cc1.web.ApiEngine;

public class App
{
    public static void main(String[] args)
    {
        // Starts the console application thread
        new InterpreterEngine(new ServicesFactory()).start();

        // Starts the API thread
        // Todo: implements (spring boot ?)
        new ApiEngine().start();
    }
}
