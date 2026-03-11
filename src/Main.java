/**
 * Book My Stay App
 * Use Case 9 - Error Handling & Validation
 *
 * Demonstrates input validation and custom exception handling
 * to prevent invalid bookings and protect system state.
 *
 * @author Malay
 * @version 9.0
 */

import java.util.*;

// Custom Exception for invalid booking scenarios
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
record Reservation(String guestName, String roomType) {

}

// Inventory class
class RoomInventory {

    private final Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public boolean isValidRoomType(String roomType) {
        return inventory.containsKey(roomType);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void allocateRoom(String roomType) throws InvalidBookingException {

        int available = getAvailability(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for " + roomType);
        }

        inventory.put(roomType, available - 1);
    }

    public void displayInventory() {

        System.out.println("\nCurrent Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Validator class
class InvalidBookingValidator {

    public static void validateReservation(
            Reservation reservation,
            RoomInventory inventory
    ) throws InvalidBookingException {

        if (reservation.guestName() == null ||
                reservation.guestName().trim().isEmpty()) {

            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.isValidRoomType(reservation.roomType())) {

            throw new InvalidBookingException(
                    "Invalid room type: " + reservation.roomType()
            );
        }

        if (inventory.getAvailability(reservation.roomType()) <= 0) {

            throw new InvalidBookingException(
                    "Requested room type is fully booked."
            );
        }
    }
}

// Main Application
public class Main {

    static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App v9.0");
        System.out.println(" Error Handling & Validation");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();

// Example reservations (valid and invalid)
        Reservation r1 = new Reservation("Sarthak", "Single Room");
        Reservation r2 = new Reservation("", "Double Room"); // Invalid name
        Reservation r3 = new Reservation("Malay", "Luxury Room"); // Invalid room type
        Reservation r4 = new Reservation("Aryan", "Suite Room");

        Reservation[] reservations = { r1, r2, r3, r4 };

        for (Reservation r : reservations) {

            try {

                System.out.println("\nProcessing booking for: " + r.guestName());

                InvalidBookingValidator.validateReservation(r, inventory);

                inventory.allocateRoom(r.roomType());

                System.out.println("Booking confirmed for " +
                        r.guestName() +
                        " (" + r.roomType() + ")");

            } catch (InvalidBookingException e) {

                System.out.println("Booking failed: " + e.getMessage());
            }
        }

// Display final inventory
        inventory.displayInventory();

        System.out.println("\nSystem continued running safely after errors.");
    }
}
