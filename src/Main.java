/**
 * Book My Stay App
 * Use Case 10 - Booking Cancellation & Inventory Rollback
 *
 * Demonstrates safe cancellation of confirmed bookings using
 * Stack (LIFO) rollback and controlled inventory restoration.
 *
 * @author Malay
 * @version 10.0
 */

import java.util.*;

// Reservation class
record Reservation(String reservationId, String guestName, String roomType, String roomId) {

}

// Inventory Service
class RoomInventory {

    private final Map<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void incrementRoom(String roomType) {

        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public void displayInventory() {

        System.out.println("\nCurrent Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Booking History
class BookingHistory {

    private final Map<String, Reservation> reservations;

    public BookingHistory() {
        reservations = new HashMap<>();
    }

    public void addReservation(Reservation r) {
        reservations.put(r.reservationId(), r);
    }

    public Reservation getReservation(String reservationId) {
        return reservations.get(reservationId);
    }

    public void removeReservation(String reservationId) {
        reservations.remove(reservationId);
    }
}

// Cancellation Service
class CancellationService {

    private final RoomInventory inventory;
    private final BookingHistory history;

    // Stack for rollback
    private final Stack<String> rollbackStack;

    public CancellationService(RoomInventory inventory, BookingHistory history) {

        this.inventory = inventory;
        this.history = history;

        rollbackStack = new Stack<>();
    }

    public void cancelBooking(String reservationId) {

        Reservation reservation = history.getReservation(reservationId);

        if (reservation == null) {

            System.out.println("Cancellation failed: Reservation does not exist.");
            return;
        }

// Push room ID to rollback stack
        rollbackStack.push(reservation.roomId());

// Restore inventory
        inventory.incrementRoom(reservation.roomType());

// Remove reservation from history
        history.removeReservation(reservationId);

        System.out.println("\nBooking Cancelled Successfully");
        System.out.println("Guest : " + reservation.guestName());
        System.out.println("Released Room ID : " + reservation.roomId());
    }

    public void showRollbackHistory() {

        System.out.println("\nRollback Stack (Recently Released Rooms):");

        for (String roomId : rollbackStack) {
            System.out.println(roomId);
        }
    }
}

// Main Application
public class Main {

    static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App v10.0");
        System.out.println(" Booking Cancellation & Rollback");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

// Example confirmed reservations
        history.addReservation(new Reservation("RES101", "Sarthak", "Single Room", "SR101"));
        history.addReservation(new Reservation("RES102", "Aryan", "Double Room", "DR201"));

        CancellationService cancellationService =
                new CancellationService(inventory, history);

// Guest requests cancellation
        cancellationService.cancelBooking("RES101");

// Invalid cancellation attempt
        cancellationService.cancelBooking("RES999");

// Display rollback stack
        cancellationService.showRollbackHistory();

// Show updated inventory
        inventory.displayInventory();
    }
}

