package at.htl.travelagency.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(
        name = "Travel.getAll",
        query = "select t from Travel t"
)
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelId;
    private String destination;
    private BigDecimal price;
    private LocalDate flightDate;
    @OneToMany(mappedBy = "travel")
    private List<Booking> bookings;
    @OneToMany(mappedBy = "travel")
    private List<Advice> advices;

    public Travel() {
        bookings = new ArrayList<>();
    }

    public Travel(String destination, BigDecimal price, LocalDate flightDate) {
        bookings = new ArrayList<>();
        this.destination = destination;
        this.price = price;
        this.flightDate = flightDate;
    }

    public Long getTravelId() {
        return travelId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public List<Booking> getBookings(){
        return bookings;
    }

    public void removeBooking(Booking booking){
        bookings.remove(booking);
    }

    public List<Advice> getAdvices() {
        return advices;
    }

    public void addAdvice(Advice advice){
        advices.add(advice);
    }

    @Override
    public String toString() {
        return "Travel{" +
                "travelId=" + travelId +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", flightDate=" + flightDate +
                '}';
    }
}
