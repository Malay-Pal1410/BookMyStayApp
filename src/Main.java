/**
 * Book My Stay App
 * Use Case 5 - Booking Request Queue (First-Come-First-Served)
 *
 * Demonstrates how booking requests are collected using a Queue
 * to preserve arrival order before allocation.
 *
 * @author Malay
 * @version 5.0
 */

import java.util.LinkedList;
import java.util.Queue;

// Reservation class representing a guest booking request
class Reservation {

    private final String guestName;
    private final String roomType;

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

    public void displayReservation() {
        System.out.println("Guest Name : " + guestName);
        System.out.println("Room Type : " + roomType);
        System.out.println("---------------------------");
    }
}

// Booking Request Queue class
class BookingRequestQueue {

    private final Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    // Display all queued requests
    public void displayRequests() {

        System.out.println("\nCurrent Booking Requests (FIFO Order):\n");

        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}

// Main Application
public class Main {

    static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App v5.0");
        System.out.println(" Booking Request Queue");
        System.out.println("=================================");

// Initialize booking queue
        BookingRequestQueue bookingQueue = getBookingRequestQueue();

// Display queue order
        bookingQueue.displayRequests();

        System.out.println("All requests stored in arrival order.");
        System.out.println("No room allocation performed yet.");
    }

    private static BookingRequestQueue getBookingRequestQueue() {
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

// Guests submit booking requests
        Reservation r1 = new Reservation("Sarthak", "Single Room");
        Reservation r2 = new Reservation("Aryan", "Double Room");
        Reservation r3 = new Reservation("Shivendra", "Suite Room");
        Reservation r4 = new Reservation("Malay", "Single Room");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);
        bookingQueue.addRequest(r4);
        return bookingQueue;
    }
}
