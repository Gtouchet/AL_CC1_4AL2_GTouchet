package esgi.al.cc1;

import esgi.al.cc1.console.InterpreterEngine;
import esgi.al.cc1.factories.ControllersFactory;

public class App
{
    public static void main(String[] args)
    {
        new InterpreterEngine(new ControllersFactory()).run();
    }
}
