package at.htl.travelagency.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(
        name = "Customer.getAll",
        query = "select c from Customer c"
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "customer", cascade=CascadeType.REMOVE)
    private List<Booking> bookings;
    @OneToMany(mappedBy = "customer", cascade=CascadeType.REMOVE)
    private List<Advice> advices;

    public Customer(){
        advices = new ArrayList<Advice>();
        bookings = new ArrayList<Booking>();
    }

    public Customer(String firstName, String lastName) {
        advices = new ArrayList<Advice>();
        bookings = new ArrayList<Booking>();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getCustomerId() {
        return customerId;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString(){
        return getLastName() + " " + getFirstName();
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Transactional
    public List<Advice> getAdvices() {
        return advices;
    }
    @Transactional
    public void addAdvice(Advice advice){
        advices.add(advice);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    @Transactional
    public void addBooking(Booking booking){
        bookings.add(booking);
    }
}
