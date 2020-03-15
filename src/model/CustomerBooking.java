package model;

import java.time.LocalDate;
import java.util.List;

public class CustomerBooking {
    private String customerId;
    private String bookingDate;
    private String route;
    private List<String> seats;

    public CustomerBooking() {
    }

    public CustomerBooking(String customerId, String bookingDate, String route, List<String> seats) {
        this.customerId = customerId;
        this.bookingDate = bookingDate;
        this.route = route;
        this.seats = seats;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "CustomerBooking{" +
                "customerId='" + customerId + '\'' +
                ", bookingDate=" + bookingDate +
                ", route='" + route + '\'' +
                ", seats=" + seats +
                '}';
    }
}
