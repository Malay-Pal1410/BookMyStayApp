/**
 * Book My Stay App
 * Use Case 3 - Centralized Room Inventory Management
 *
 * Demonstrates centralized room availability using HashMap.
 * Inventory logic is encapsulated inside RoomInventory class.
 *
 * @author Malay
 * @version 3.1
 */

import java.util.HashMap;
import java.util.Map;

// RoomInventory class responsible for managing room availability
class RoomInventory {

    // HashMap storing room type and available count
    private HashMap<String, Integer> inventory;

    // Constructor initializes room availability
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Method to get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Method to update room availability
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Method to display entire inventory
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}

// Main Application Class
public class Main {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App v3.1");
        System.out.println(" Centralized Room Inventory");
        System.out.println("=================================");

// Initialize inventory system
        RoomInventory inventory = new RoomInventory();

// Display current inventory
        inventory.displayInventory();

// Example update
        System.out.println("\nUpdating Single Room availability...");

        inventory.updateAvailability("Single Room", 4);

// Display updated inventory
        inventory.displayInventory();

        System.out.println("\nApplication terminated.");
    }
}
