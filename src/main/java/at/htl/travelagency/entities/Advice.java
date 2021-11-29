package at.htl.travelagency.entities;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@NamedQuery(
        name = "Advice.getAll",
        query = "select a from Advice a"
)
public class Advice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adviceId;
    @ManyToOne
    @JoinColumn(name = "Employee_ID")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "Travel_ID")
    private Travel travel;
    @ManyToOne
    @JoinColumn(name = "Customer_ID")
    private Customer customer;

    public Advice() { }

    public Long getAdviceId() {
        return adviceId;
    }

    public Advice(Travel travel) {
        this.travel = travel;
    }

    public Travel getTravel() {
        return travel;
    }

    @Transactional
    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Transactional
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Transactional
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
