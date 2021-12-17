package esgi.al.gtouchet.cc2;

import esgi.al.gtouchet.cc2.application.services.ServicesContainer;
import esgi.al.gtouchet.cc2.console.engine.CommandProcessor;
import esgi.al.gtouchet.cc2.console.engine.InterpreterEngine;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.DataRepositoriesFactory;
import esgi.al.gtouchet.cc2.web.ApiEngine;

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

        // Starts the API thread Todo: implements quarkus
        new ApiEngine(servicesContainer).start();
    }
}
