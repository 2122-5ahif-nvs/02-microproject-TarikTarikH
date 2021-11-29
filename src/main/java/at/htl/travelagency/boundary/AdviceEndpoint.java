package at.htl.travelagency.boundary;

import at.htl.travelagency.entities.Advice;
import at.htl.travelagency.repositories.AdviceRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/advice-api")
@Tag(name = "Advice")
public class AdviceEndpoint {
    @Inject
    AdviceRepository repository;

    @GET
    @Path("/read-advices")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Advice> readAdvices(){
        return repository.findAllEntities();
    }
}
