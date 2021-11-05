package esgi.al;

import esgi.al.cliInterpreter.CliInterpreterEngine;
import esgi.al.factories.ControllersFactory;

public class App
{
    public static void main(String[] args)
    {
        new CliInterpreterEngine(new ControllersFactory()).launch();
    }
}
