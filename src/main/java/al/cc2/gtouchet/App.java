package al.cc2.gtouchet;

import al.cc2.gtouchet.application.services.ServicesContainer;
import al.cc2.gtouchet.console.engine.CommandProcessor;
import al.cc2.gtouchet.console.engine.InterpreterEngine;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidatorApi;
import al.cc2.gtouchet.infrastructure.repositories.factories.DataRepositoriesFactory;

public class App
{
    public static void main(String[] args)
    {
        // Register all application services
        ServicesContainer servicesContainer = ServicesContainer.initialize(
                new DataRepositoriesFactory(),
                new PasswordValidator(),
                new PaymentMethodValidatorApi()
        );

        // Starts the console application thread
        new InterpreterEngine(new CommandProcessor(servicesContainer)).start();
    }
}
