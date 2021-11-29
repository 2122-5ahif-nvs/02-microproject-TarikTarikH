package at.htl.travelagency.boundary;

import at.htl.travelagency.entities.Booking;
import at.htl.travelagency.entities.Customer;
import at.htl.travelagency.entities.Travel;
import at.htl.travelagency.repositories.BookingRepository;
import at.htl.travelagency.repositories.CustomerRepository;
import at.htl.travelagency.repositories.TravelRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customer-api")
@Tag(name = "Customer")
public class CustomerEndpoint {
    @Inject
    CustomerRepository customerRepository;
    @Inject
    BookingRepository bookingRepository;
    @Inject
    TravelRepository travelRepository;

    @Operation(
            summary = "Displays customers",
            description = "Reads and returns a list of customers"
    )
    @GET
    @Path("read-customers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> readCustomers(){
        return customerRepository.findAllEntities();
    }

    @Operation(
            summary = "Adds new customers",
            description = "Adds a new customer to the repository"
    )
    @POST
    @Path("create-customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewCustomer(Customer customer){
        customerRepository.saveEntity(customer);
        return Response.ok().build();
    }

    @Operation(
            summary = "Deletes a customer",
            description = "Deletes a customer by its id"
    )
    @DELETE
    @Path("delete-customer/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") Long id){

        customerRepository.removeEntity(id);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create-Booking/{customerid}/{travelid}")
    public Response createBooking(@PathParam("customerid") int customerId,
                                  @PathParam("travelid") int travelId,
                                  Booking booking){
         booking.setCustomer(customerRepository.findById(Long.valueOf(customerId)));
         booking.setTravel(travelRepository.findEntity(Travel.class, Long.valueOf(travelId)));

         bookingRepository.saveEntity(booking);
         travelRepository.findEntity(Travel.class, Long.valueOf(travelId)).addBooking(booking);
         customerRepository.findEntity(Customer.class, Long.valueOf(customerId)).addBooking(booking);

         return Response.ok().build();
    }

}