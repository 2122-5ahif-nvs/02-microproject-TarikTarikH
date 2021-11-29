package at.htl.travelagency.boundary;

import at.htl.travelagency.repositories.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/reset-db")
public class ResetDbEndpoint {
    @Inject
    TravelRepository travelRepository;

    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    CustomerRepository customerRepository;

    @Inject
    BookingRepository bookingRepository;

    @Inject
    AdviceRepository adviceRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response resetDb(){

        System.out.println("ENTERED METHOD");

        travelRepository.resetTable();
        customerRepository.resetTable();
        employeeRepository.resetTable();
        adviceRepository.resetTable();
        bookingRepository.resetTable();

        return Response.ok("DB reset...").build();
    }
}
