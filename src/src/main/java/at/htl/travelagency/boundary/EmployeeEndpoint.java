package at.htl.travelagency.boundary;

import at.htl.travelagency.dto.EmployeeDtoFirstName;
import at.htl.travelagency.entities.Advice;
import at.htl.travelagency.entities.Employee;
import at.htl.travelagency.entities.Travel;
import at.htl.travelagency.repositories.AdviceRepository;
import at.htl.travelagency.repositories.CustomerRepository;
import at.htl.travelagency.repositories.EmployeeRepository;
import at.htl.travelagency.repositories.TravelRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

@Path("/employee-api")
@Tag(name = "Employee")
public class EmployeeEndpoint {
    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    AdviceRepository adviceRepository;
    @Inject
    TravelRepository travelRepository;
    @Inject
    CustomerRepository customerRepository;

    @Operation(
            summary = "Displays employees",
            description = "Reads and returns a list of employees"
    )
    @GET
    @Path("read-emps")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> readEmps(){
        return employeeRepository.findAllEntities();
    }

    @Operation(
            summary = "Adds new employees",
            description = "Adds a new employee to the repository"
    )
    @POST
    @Path("add-emp")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewEmp(Employee emp){
        employeeRepository.saveEntity(emp);
        return Response.ok().build();
    }

    @Operation(
            summary = "Deletes an employee",
            description = "Deletes an employee by its id"
    )
    @DELETE
    @Path("delete-emp/{id}")
    public Response deleteEmp(@PathParam("id") Long id){
        employeeRepository.removeEntity(id);
        return Response.ok().build();
    }

    @DELETE
    @Path("delete-emp")
    public Response deleteEmpWithQueryParam(@QueryParam("id") Long id){
        employeeRepository.removeEntity(id);

        return Response.ok().build();
    }
    @POST
    @Path("create-advice/{customerId}/{employeeId}/{destination}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createAdvice(@PathParam("customerId") int customerId,
                               @PathParam("employeeId") int employeeId,
                               @PathParam("destination") String destination) {
        Travel travel = null;
        try {
            travel = travelRepository.findAdviceByDestination(destination);
            adviceRepository.saveEntity(new Advice(travel));

            var advice = adviceRepository.findAllEntities().get(adviceRepository.findAllEntities().size() - 1);
            var customer = customerRepository.findById(Long.valueOf(customerId));
            var employee = employeeRepository.findEntity(Employee.class, Long.valueOf(employeeId));

            advice.setEmployee(employee);
            advice.setCustomer(customer);
            employee.addAdvice(advice);

            travel.addAdvice(advice);

        }catch(Exception e){
            e.printStackTrace();
        }

        return Response.ok().build();
    }

    @GET
    @Path("/calcualte-sales/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public BigDecimal calculateSales(@PathParam("id") int id){
        return employeeRepository.calculateSales(Long.valueOf(id));
    }

    @POST
    @Path("/insert-multiple-employees")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response insertEmployees(List<Employee> employees){
        return Response.ok(this.employeeRepository.saveEntities(employees).toArray()).build();
    }

    @PATCH
    @Path("/update-firstname")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFirstName(EmployeeDtoFirstName dto){
        return Response.ok(this.employeeRepository.patchEmployee(dto)).build();
    }
}