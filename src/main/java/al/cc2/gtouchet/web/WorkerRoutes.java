package al.cc2.gtouchet.web;

import al.cc2.gtouchet.application.services.HandlersContainer;
import al.cc2.gtouchet.application.services.dtos.worker.CreateWorkerCommand;
import al.cc2.gtouchet.application.services.dtos.worker.DeleteWorkerCommand;
import al.cc2.gtouchet.application.services.dtos.worker.ReadWorkerQuery;
import al.cc2.gtouchet.application.services.handlers.worker.CreateWorkerCommandHandler;
import al.cc2.gtouchet.application.services.handlers.worker.DeleteWorkerByIdCommandHandler;
import al.cc2.gtouchet.application.services.handlers.worker.ReadAllWorkerQueryHandler;
import al.cc2.gtouchet.application.services.handlers.worker.ReadWorkerByIdQueryHandler;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.domain.valueObjects.EntityId;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidatorApi;
import al.cc2.gtouchet.infrastructure.repositories.factories.DataRepositoriesFactory;

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
    public Response create(CreateWorkerCommand createWorkerCommand) // TODO
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
        Worker worker = (Worker) this.handlersContainer.getQueryHandler(ReadWorkerByIdQueryHandler.class).handle(new ReadWorkerQuery(
                EntityId.fromString(id)
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
        boolean success = (boolean) this.handlersContainer.getCommandHandler(DeleteWorkerByIdCommandHandler.class).handle(new DeleteWorkerCommand(
                EntityId.fromString(id)
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
        this.login = worker.getCredentials().getLogin();
        this.password = worker.getCredentials().getPassword().toString();
        this.name = worker.getName();
        this.service = worker.getService().toString();
        this.department = worker.getDepartment();
        this.creationDate = worker.getCreationDate().toString();
        this.updateDate = worker.getUpdateDate().toString();
    }
}
