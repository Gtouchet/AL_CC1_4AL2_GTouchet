package esgi.al.cc1;

import esgi.al.cc1.application.engine.InterpreterEngine;
import esgi.al.cc1.infrastructure.factories.ControllersFactory;

public class App
{
    public static void main(String[] args)
    {
        // Starts the console application thread
        new InterpreterEngine(new ControllersFactory()).start();

        // Starts the API thread
        // Todo: implements
    }
}
