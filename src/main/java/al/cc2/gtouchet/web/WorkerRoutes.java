package al.cc2.gtouchet.web;

import al.cc2.gtouchet.application.services.HandlersContainer;
import al.cc2.gtouchet.application.services.dtos.worker.CreateWorkerCommand;
import al.cc2.gtouchet.application.services.dtos.worker.DeleteWorkerCommand;
import al.cc2.gtouchet.application.services.dtos.worker.ReadWorkerQuery;
import al.cc2.gtouchet.application.services.handlers.worker.CreateWorkerCommandHandler;
import al.cc2.gtouchet.application.services.handlers.worker.DeleteWorkerCommandHandler;
import al.cc2.gtouchet.application.services.handlers.worker.ReadAllWorkerQueryHandler;
import al.cc2.gtouchet.application.services.handlers.worker.ReadWorkerQueryHandler;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidatorApi;
import al.cc2.gtouchet.infrastructure.repositories.factories.DataRepositoriesFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/worker")
public class WorkerRoutes
{
    private final HandlersContainer handlersContainer;

    public WorkerRoutes()
    {
        this.handlersContainer = HandlersContainer.initialize(
                new DataRepositoriesFactory(),
                new PasswordValidator(),
                new PaymentMethodValidatorApi()
        );
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid CreateWorkerCommand createWorkerCommand) // TODO
    {
        Worker worker = (Worker) this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(createWorkerCommand);
        return Response.status(Response.Status.CREATED).entity(worker).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll()
    {
        List<Worker> workers = (List<Worker>) this.handlersContainer.getQueryHandler(ReadAllWorkerQueryHandler.class).handle(null);
        List<WorkerJson> workersJson = new ArrayList<>();
        workers.forEach(worker -> workersJson.add(new WorkerJson(worker)));
        return Response.ok().entity(workersJson).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readId(@PathParam("id") String id)
    {
        Worker worker = (Worker) this.handlersContainer.getQueryHandler(ReadWorkerQueryHandler.class).handle(new ReadWorkerQuery(
                Id.fromString(id)
        ));

        if (worker == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new WorkerJson(worker)).build();
    }

    // TODO PUT

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") String id)
    {
        boolean success = (boolean) this.handlersContainer.getCommandHandler(DeleteWorkerCommandHandler.class).handle(new DeleteWorkerCommand(
                Id.fromString(id)
        ));
        return Response.ok(success ? "Worker " + id + " deleted" : "Could not delete worker " + id).build();
    }
}

class WorkerJson
{
    public final String id;
    public final String login;
    public final String password;
    public final String name;
    public final String service;
    public final int department;
    public final String creationDate;
    public final String updateDate;

    public WorkerJson(Worker worker)
    {
        this.id = worker.getId().toString();
        this.login = worker.getLogin();
        this.password = worker.getPassword().toString();
        this.name = worker.getName();
        this.service = worker.getService().toString();
        this.department = worker.getDepartment();
        this.creationDate = worker.getCreationDate().toString();
        this.updateDate = worker.getUpdateDate().toString();
    }
}