package esgi.al.cc1;

import esgi.al.cc1.domain.dtos.Id;
import esgi.al.cc1.domain.enumerators.PaymentMethod;
import esgi.al.cc1.infrastructure.factories.ControllersFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContractorControllerTests extends RoomCleaner
{
    private final ControllersFactory controllersFactory = new ControllersFactory();

    @Test
    public void createContractor()
    {
        // Get the repo's actual size
        long initialRepoSize = this.controllersFactory.createContractorController().read().count();
        // Add a new contractor in the repo
        Id newContractorId = this.controllersFactory.createContractorController().create(new String[] {"",
                "123_Touchet_456",
                "__password789__",
                "Guillaume",
                "paypal",
        });
        // Get the repo's new size
        long newRepoSize = this.controllersFactory.createContractorController().read().count();

        // Check the difference between old and new sizes
        assertEquals(1, newRepoSize - initialRepoSize);

        this.cleanFakeContractor(newContractorId);
    }

    @Test
    public void createContractor_updateContractor()
    {
        Id newContractorId = this.controllersFactory.createContractorController().create(new String[] {"",
                "123_Touchet_456",
                "__password789__",
                "Guillaume",
                "paypal",
        });

        // Contractor's name is "Guillaume" and payment method is "paypal"
        assertEquals("Guillaume", this.controllersFactory.createContractorController().read(newContractorId.toString()).getName());
        assertEquals(PaymentMethod.paypal, this.controllersFactory.createContractorController().read(newContractorId.toString()).getPaymentMethod());
        // Updating the contractor's name and payment method
        this.controllersFactory.createContractorController().update(new String[] {"",
                newContractorId.toString(),
                "__password789__",
                "Robert",
                "card",
        });
        // Contractor's name should be "Robert" and payment method should be "card" now
        assertEquals("Robert", this.controllersFactory.createContractorController().read(newContractorId.toString()).getName());
        assertEquals(PaymentMethod.card, this.controllersFactory.createContractorController().read(newContractorId.toString()).getPaymentMethod());

        this.cleanFakeContractor(newContractorId);
    }

    @Test
    public void createContractor_validatePaymentMethod()
    {
        Id newContractorId = this.controllersFactory.createContractorController().create(new String[] {"",
                "123_Touchet_456",
                "__password789__",
                "Guillaume",
                "paypal",
        });

        // The payment method is not yet validated
        assertFalse(this.controllersFactory.createContractorController().read(newContractorId.toString()).isPaymentValidated());
        // Validate the payment method
        this.controllersFactory.createContractorController().validatePayment(newContractorId.toString());
        // It is now validated
        assertTrue(this.controllersFactory.createContractorController().read(newContractorId.toString()).isPaymentValidated());

        this.cleanFakeContractor(newContractorId);
    }
}
