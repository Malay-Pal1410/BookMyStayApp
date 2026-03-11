/**
 * Book My Stay App
 * Use Case 4 - Room Search & Availability Check
 *
 * Demonstrates read-only access to centralized inventory
 * and filtering of available room types.
 *
 * @author Malay
 * @version 4.0
 */

import java.util.HashMap;
import java.util.Map;

// Abstract Room class
abstract class Room {

    protected String roomType;
    protected int beds;
    protected int size;
    protected double price;

    public Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds : " + beds);
        System.out.println("Size : " + size + " sq ft");
        System.out.println("Price : $" + price);
    }

    public String getRoomType() {
        return roomType;
    }
}

// Concrete room types
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200, 80.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 300, 120.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 500, 250.0);
    }
}

// Centralized inventory (read-only usage here)
class RoomInventory {

    private final HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // Example unavailable
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllInventory() {
        return inventory;
    }
}

// Search service (read-only operations)
class RoomSearchService {

    private final RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms(Room[] rooms) {

        System.out.println("\nAvailable Rooms:\n");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

// Validation: show only rooms with availability > 0
            if (available > 0) {

                room.displayDetails();
                System.out.println("Available : " + available);
                System.out.println("-------------------------");
            }
        }
    }
}

// Main Application
public class Main {

    static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App v4.0");
        System.out.println(" Room Search & Availability");
        System.out.println("=================================");

// Initialize inventory
        RoomInventory inventory = new RoomInventory();

// Create room objects
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

// Search service
        RoomSearchService searchService = new RoomSearchService(inventory);

// Guest initiates room search
        searchService.searchAvailableRooms(rooms);

        System.out.println("\nSearch completed. Inventory unchanged.");
    }
}
