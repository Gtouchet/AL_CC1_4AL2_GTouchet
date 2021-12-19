package al.cc2.gtouchet.builders;

import al.cc2.gtouchet.domain.builders.PaymentBuilder;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.models.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class PaymentBuilderTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void buildPayment()
    {
        Id contractorId = Id.generate();
        Id workerId = Id.generate();

        Payment payment = PaymentBuilder.init(Id.generate(), Date.now())
                .setContractorId(contractorId)
                .setWorkerId(workerId)
                .setPaymentMethod(PaymentMethod.card)
                .setAmount(1)
                .setReason("anyReason")
                .build();

        assertNotNull(payment);

        assertEquals(contractorId, payment.getContractorId());
        assertEquals(workerId, payment.getWorkerId());
        assertEquals(PaymentMethod.card, payment.getPaymentMethod());
        assertEquals(1, payment.getAmount(), 0.0);
        assertEquals("anyReason", payment.getReason());
    }

    @Test
    public void buildPayment_withoutAmount()
    {
        Payment payment = PaymentBuilder.init(Id.generate(), Date.now())
                .setContractorId(Id.generate())
                .setWorkerId(Id.generate())
                .setPaymentMethod(PaymentMethod.card)
                // Payment amount set to Double's default if not specified (0.0)
                .setReason("anyReason")
                .build();

        assertNotNull(payment);

        assertEquals(0.0, payment.getAmount(), 0.0);
    }

    @Test
    public void buildPayment_missingOneProperty()
    {
        exception.expect(NullPointerException.class);

        PaymentBuilder.init(Id.generate(), Date.now())
                .setContractorId(Id.generate())
                //.setWorkerId(Id.generate())
                .setPaymentMethod(PaymentMethod.card)
                .setAmount(1)
                .setReason("anyReason")
                .build();
    }
}
