/**
 * Book My Stay App
 * Use Case 2 - Basic Room Types & Static Availability
 *
 * Demonstrates abstraction, inheritance, polymorphism,
 * and static availability representation.
 *
 * @author Malay
 * @version 2.1
 */

// Abstract Room Class
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

    // Method to display room details
    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds : " + beds);
        System.out.println("Size : " + size + " sq ft");
        System.out.println("Price : $" + price);
    }
}

// Single Room Class
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 80.0);
    }
}

// Double Room Class
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 300, 120.0);
    }
}

// Suite Room Class
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 500, 250.0);
    }
}

// Main Application Class
public class Main {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App v2.1");
        System.out.println(" Room Types & Availability");
        System.out.println("=================================");

// Static availability variables
        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

// Polymorphism
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        System.out.println("\n--- Single Room Details ---");
        single.displayRoomDetails();
        System.out.println("Available : " + singleRoomAvailable);

        System.out.println("\n--- Double Room Details ---");
        doubleRoom.displayRoomDetails();
        System.out.println("Available : " + doubleRoomAvailable);

        System.out.println("\n--- Suite Room Details ---");
        suite.displayRoomDetails();
        System.out.println("Available : " + suiteRoomAvailable);

        System.out.println("\nApplication terminated.");
    }
}


