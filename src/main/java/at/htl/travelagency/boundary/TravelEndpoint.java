package at.htl.travelagency.boundary;

import at.htl.travelagency.repositories.TravelRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/travel-api")
@Tag(name = "Travel")
public class TravelEndpoint {
    @Inject
    TravelRepository travelRepository;

    @POST
    @Path("/init")
    public Response init(){
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAllTravels(){
        return Response.ok(travelRepository.findAllEntities()).build();
    }
}
