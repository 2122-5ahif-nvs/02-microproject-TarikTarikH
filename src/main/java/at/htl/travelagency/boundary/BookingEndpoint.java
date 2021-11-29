package at.htl.travelagency.boundary;

import at.htl.travelagency.entities.Booking;
import at.htl.travelagency.repositories.BookingRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/booking-api")
@Tag(name = "Booking")
public class BookingEndpoint {
    @Inject
    BookingRepository bookingRepository;

    @GET
    @Path("/read-bookings")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> readBookings(){
        return bookingRepository.findAllEntities();
    }
}
