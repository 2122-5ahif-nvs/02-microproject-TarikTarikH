package at.htl.travelagency.entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Employee")
@NamedQuery(
        name = "Employee.getAll",
        query = "select e from Employee e"
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate hireDate;
    @OneToMany(mappedBy = "employee")
    @JsonbTransient
    private List<Advice> advices;

    public Employee(String firstName, String lastName, LocalDate hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
    }

    public Employee(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public List<Advice> getAdvices() {
        return advices;
    }

    public void addAdvice(Advice advice){
        advices.add(advice);
    }



}