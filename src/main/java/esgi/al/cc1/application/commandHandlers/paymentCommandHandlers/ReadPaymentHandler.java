package esgi.al.cc1.application.commandHandlers.paymentCommandHandlers;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.console.Command;
import esgi.al.cc1.controllers.Controller;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.exceptions.repositoriesExceptions.ElementNotFound;

import java.util.List;
import java.util.stream.Collectors;

public class ReadPaymentHandler implements CommandHandler
{
    private final Controller<Payment> paymentController;

    public ReadPaymentHandler(Controller<Payment> paymentController)
    {
        this.paymentController = paymentController;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgument
    {
        if (params.length == Command.readPayment.parametersCount)
        {
            List<Payment> payments = this.paymentController.read().collect(Collectors.toList());
            if (payments.size() == 0) {
                System.out.println("No payment registered yet.");
            }
            else {
                payments.forEach(System.out::println);
            }
        }

        else if (params.length == Command.readPayment.parametersOverloadCount)
        {
            try {
                System.out.println(this.paymentController.read(params[1]));
            } catch (ElementNotFound e) {
                System.out.println(e.getMessage());
            }
        }

        else
        {
            throw new WrongNumberOfArgument(Command.readPayment);
        }
    }
}