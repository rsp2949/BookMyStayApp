import java.util.*;

/*
 ============================================================
 CLASS - Reservation
 ============================================================
*/
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

/*
 ============================================================
 CLASS - BookingHistory
 ============================================================
*/
class BookingHistory {

    // Stores confirmed reservations in order
    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    // Add confirmed booking
    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    // Get all bookings
    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

/*
 ============================================================
 CLASS - BookingReportService
 ============================================================
*/
class BookingReportService {

    public void generateReport(BookingHistory history) {

        System.out.println("Booking History Report");

        for (Reservation r : history.getConfirmedReservations()) {
            System.out.println(
                    "Guest: " + r.getGuestName() +
                            ", Room Type: " + r.getRoomType()
            );
        }
    }
}

/*
 ============================================================
 MAIN CLASS - UC8
 ============================================================
*/
public class Bookmystayapp {

    public static void main(String[] args) {

        System.out.println("Booking History and Reporting\n");

        // Initialize history
        BookingHistory history = new BookingHistory();

        // Add confirmed bookings
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}