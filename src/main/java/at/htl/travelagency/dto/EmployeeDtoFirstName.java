package at.htl.travelagency.dto;

public class EmployeeDtoFirstName {
    private String firstName;
    private Long id;

    public EmployeeDtoFirstName() {
    }

    public EmployeeDtoFirstName(String firstName, Long id) {
        this.firstName = firstName;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
