package esgi.al.cc1.application.commandHandlers.payment;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.enumerators.Command;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.infrastructure.controllers.Controller;

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
        if (params.length == Command.readPayment.parameters)
        {
            List<Payment> payments = this.paymentController.read().collect(Collectors.toUnmodifiableList());
            if (payments.size() == 0)
            {
                System.out.println("No payment registered yet");
            }
            else
            {
                payments.forEach(System.out::println);
            }
        }
        else if (params.length == Command.readPayment.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            System.out.println(this.paymentController.read(params[1]));
        }
        else
        {
            throw new WrongNumberOfArgument(Command.readPayment);
        }
    }
}