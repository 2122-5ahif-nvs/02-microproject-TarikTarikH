package at.htl.travelagency.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NamedQuery(
        name = "Booking.getAll",
        query = "select b from Booking b"
)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private LocalDate bookingDate;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "Customer_ID")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "Travel_ID")
    private Travel travel;

    public Booking() {
    }

    public Booking(LocalDate bookingDate, BigDecimal price, Travel travel) {
        this.bookingDate = bookingDate;
        this.price = price;
        this.travel = travel;
    }

    public Booking(LocalDate bookingDate, BigDecimal price) {
        this.bookingDate = bookingDate;
        this.price = price;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return getBookingId() + "\t" + getBookingDate() + "\t" + getPrice();
    }
}
