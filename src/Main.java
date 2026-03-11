/**
 * Book My Stay App
 * Use Case 6 - Reservation Confirmation & Room Allocation
 *
 * Demonstrates safe room allocation using Queue, HashMap, and Set
 * to prevent double booking and maintain inventory consistency.
 *
 * @author Malay
 * @version 6.0
 */

import java.util.*;

// Reservation class representing a booking request
record Reservation(String guestName, String roomType) {

}

// Inventory Service
class RoomInventory {

    private final HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Booking Service
class BookingService {

    private final Queue<Reservation> bookingQueue;
    private final RoomInventory inventory;

    // Map room type -> allocated room IDs
    private final HashMap<String, Set<String>> allocatedRooms;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        bookingQueue = new LinkedList<>();
        allocatedRooms = new HashMap<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        bookingQueue.add(reservation);
    }

    // Generate unique room ID
    private String generateRoomId(String roomType) {
        int number = new Random().nextInt(900) + 100;
        return roomType.replace(" ", "").substring(0, 2).toUpperCase() + number;
    }

    // Process booking queue
    public void processBookings() {

        System.out.println("\nProcessing Booking Requests...\n");

        while (!bookingQueue.isEmpty()) {

            Reservation r = bookingQueue.poll();
            String roomType = r.roomType();

            int available = inventory.getAvailability(roomType);

            if (available > 0) {

                String roomId;

                do {
                    roomId = generateRoomId(roomType);
                } while (isRoomIdUsed(roomType, roomId));

                allocatedRooms
                        .computeIfAbsent(roomType, k -> new HashSet<>())
                        .add(roomId);

                inventory.decrementRoom(roomType);

                System.out.println("Reservation Confirmed");
                System.out.println("Guest : " + r.guestName());
                System.out.println("Room Type : " + roomType);
                System.out.println("Room ID : " + roomId);
                System.out.println("-----------------------------");

            } else {
                System.out.println("No rooms available for " + r.guestName() +
                        " (" + roomType + ")");
            }
        }
    }

    // Check if room ID already exists
    private boolean isRoomIdUsed(String roomType, String roomId) {

        if (!allocatedRooms.containsKey(roomType))
            return false;

        return allocatedRooms.get(roomType).contains(roomId);
    }

    public void displayAllocatedRooms() {

        System.out.println("\nAllocated Rooms:");

        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {

            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

// Main Class
public class Main {

    static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App v6.0");
        System.out.println(" Reservation Confirmation System");
        System.out.println("=================================");

// Initialize inventory
        RoomInventory inventory = new RoomInventory();

// Initialize booking service
        BookingService bookingService = getBookingService(inventory);

// Show allocated rooms
        bookingService.displayAllocatedRooms();

// Show updated inventory
        inventory.displayInventory();
    }

    private static BookingService getBookingService(RoomInventory inventory) {
        BookingService bookingService = new BookingService(inventory);

// Booking requests
        bookingService.addRequest(new Reservation("Sarthak", "Single Room"));
        bookingService.addRequest(new Reservation("Aryan", "Double Room"));
        bookingService.addRequest(new Reservation("Shivendra", "Suite Room"));
        bookingService.addRequest(new Reservation("Malay", "Single Room"));
        bookingService.addRequest(new Reservation("Rohan", "Suite Room"));

// Process bookings
        bookingService.processBookings();
        return bookingService;
    }
}
