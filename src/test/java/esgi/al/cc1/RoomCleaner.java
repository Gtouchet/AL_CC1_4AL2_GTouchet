package esgi.al.cc1;

import esgi.al.cc1.domain.dtos.Id;
import esgi.al.cc1.infrastructure.factories.ControllersFactory;

public abstract class RoomCleaner
{
    private final ControllersFactory controllersFactory = new ControllersFactory();

    /**
     * I don't want my "real" data to be polluted by my tests.
     * So everytime I run a test adding an element,
     * I should call one of these methods to clean my testing data.
     * @param id The ID of the element to clean
     */
    protected void cleanFakeContractor(Id id)
    {
        this.controllersFactory.createContractorController().remove(id.toString());
    }

    protected void cleanFakePayment(Id id)
    {
        this.controllersFactory.createPaymentController().remove(id.toString());
    }

    protected void cleanFakeProject(Id id)
    {
        this.controllersFactory.createProjectController().remove(id.toString());
    }

    protected void cleanFakeWorker(Id id)
    {
        this.controllersFactory.createWorkerController().remove(id.toString());
    }
}
