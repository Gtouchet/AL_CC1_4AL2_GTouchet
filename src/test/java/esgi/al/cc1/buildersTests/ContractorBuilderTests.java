package esgi.al.cc1.buildersTests;

import esgi.al.cc1.domain.builders.ContractorBuilder;
import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ContractorBuilderTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void buildContractor()
    {
        Contractor contractor = ContractorBuilder.init(Id.generate(), "GTouchet", Date.now())
                .setPassword(Password.of("ABcd1234!"))
                .setName("Guillaume")
                .setPaymentMethod(PaymentMethod.card)
                .setIsPaymentValidated(false)
                .build();

        assertNotNull(contractor);

        assertEquals("ABcd1234!", contractor.getPassword().toString());
        assertEquals("Guillaume", contractor.getName());
        assertEquals(PaymentMethod.card, contractor.getPaymentMethod());
        assertFalse(contractor.isPaymentValidated());
    }

    @Test
    public void buildContractor_withoutPaymentValidation()
    {
        Contractor contractor = ContractorBuilder.init(Id.generate(), "GTouchet", Date.now())
                .setPassword(Password.of("ABcd1234!"))
                .setName("Guillaume")
                .setPaymentMethod(PaymentMethod.card)
                // Set to false by default if not specified
                .build();

        assertNotNull(contractor);

        assertFalse(contractor.isPaymentValidated());
    }

    @Test
    public void buildContractor_missingOneProperty()
    {
        exception.expect(NullPointerException.class);

        ContractorBuilder.init(Id.generate(), "GTouchet", Date.now())
                .setPassword(Password.of("ABcd1234!"))
                //.setName("Guillaume")
                .setPaymentMethod(PaymentMethod.card)
                .build();
    }
}
