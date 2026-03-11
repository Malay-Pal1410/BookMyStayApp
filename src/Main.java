/**
 * Book My Stay App
 * Use Case 8 - Booking History & Reporting
 *
 * Demonstrates storing confirmed bookings using List
 * and generating reports without modifying stored data.
 *
 * @author Malay
 * @version 8.0
 */

import java.util.*;

// Reservation class representing a confirmed booking
record Reservation(String reservationId, String guestName, String roomType) {

    public void displayReservation() {
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Guest Name : " + guestName);
        System.out.println("Room Type : " + roomType);
        System.out.println("-----------------------------");
    }
}

// BookingHistory class storing confirmed reservations
class BookingHistory {

    private final List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    // Retrieve all reservations
    public List<Reservation> getReservations() {
        return history;
    }
}

// Booking Report Service
class BookingReportService {

    private final BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    // Display all booking records
    public void displayAllBookings() {

        System.out.println("\nBooking History:\n");

        for (Reservation r : history.getReservations()) {
            r.displayReservation();
        }
    }

    // Generate summary report
    public void generateSummaryReport() {

        Map<String, Integer> roomCount = new HashMap<>();

        for (Reservation r : history.getReservations()) {

            roomCount.put(
                    r.roomType(),
                    roomCount.getOrDefault(r.roomType(), 0) + 1
            );
        }

        System.out.println("\nBooking Summary Report:");

        for (Map.Entry<String, Integer> entry : roomCount.entrySet()) {

            System.out.println(entry.getKey() + " Bookings : " + entry.getValue());
        }
    }
}

// Main Application
public class Main {

    static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App v8.0");
        System.out.println(" Booking History & Reporting");
        System.out.println("=================================");

// Initialize booking history
        BookingReportService reportService = getBookingReportService();

// Generate report
        reportService.generateSummaryReport();

        System.out.println("\nReporting completed without modifying booking history.");
    }

    private static BookingReportService getBookingReportService() {
        BookingHistory history = new BookingHistory();

// Example confirmed reservations
        history.addReservation(new Reservation("RES101", "Sarthak", "Single Room"));
        history.addReservation(new Reservation("RES102", "Aryan", "Double Room"));
        history.addReservation(new Reservation("RES103", "Shivendra", "Suite Room"));
        history.addReservation(new Reservation("RES104", "Malay", "Single Room"));

// Initialize reporting service
        BookingReportService reportService = new BookingReportService(history);

// Admin reviews booking history
        reportService.displayAllBookings();
        return reportService;
    }
}
