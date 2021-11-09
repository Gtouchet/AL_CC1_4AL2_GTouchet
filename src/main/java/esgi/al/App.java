package esgi.al;

import esgi.al.console.InterpreterEngine;
import esgi.al.factories.ControllersFactory;

public class App
{
    public static void main(String[] args)
    {
        new InterpreterEngine(new ControllersFactory()).run();
    }
}
