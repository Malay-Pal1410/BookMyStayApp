/**
 * Book My Stay App
 * Use Case 7 - Add-On Service Selection
 *
 * Demonstrates attaching optional services to an existing reservation
 * using Map<String, List<Service>>.
 *
 * @author Malay
 * @version 7.0
 */

import java.util.*;

// Service class representing an optional add-on service
class Service {

    private final String serviceName;
    private final double price;

    public Service(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void displayService() {
        System.out.println(serviceName + " ($" + price + ")");
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map reservation ID -> List of services
    private final Map<String, List<Service>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, Service service) {

        reservationServices
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println("Service added to reservation " + reservationId +
                ": " + service.getServiceName());
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected for reservation " + reservationId);
            return;
        }

        System.out.println("\nServices for Reservation " + reservationId + ":");

        for (Service s : services) {
            s.displayService();
        }
    }

    // Calculate total add-on cost
    public double calculateTotalCost(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null)
            return 0;

        double total = 0;

        for (Service s : services) {
            total += s.getPrice();
        }

        return total;
    }
}

// Main Class
public class Main {

    static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App v7.0");
        System.out.println(" Add-On Service Selection");
        System.out.println("=================================");

// Example reservation IDs (already confirmed earlier)
        String reservation1 = "RES101";
        String reservation2 = "RES102";

// Initialize Add-On Service Manager
        AddOnServiceManager serviceManager = getAddOnServiceManager(reservation1, reservation2);

// Calculate additional cost
        System.out.println("\nTotal Add-On Cost for " + reservation1 + " : $" +
                serviceManager.calculateTotalCost(reservation1));

        System.out.println("Total Add-On Cost for " + reservation2 + " : $" +
                serviceManager.calculateTotalCost(reservation2));

        System.out.println("\nCore booking and inventory remain unchanged.");
    }

    private static AddOnServiceManager getAddOnServiceManager(String reservation1, String reservation2) {
        AddOnServiceManager serviceManager = new AddOnServiceManager();

// Available services
        Service breakfast = new Service("Breakfast", 15);
        Service airportPickup = new Service("Airport Pickup", 40);
        Service spa = new Service("Spa Access", 60);

// Guest selects services
        serviceManager.addService(reservation1, breakfast);
        serviceManager.addService(reservation1, spa);

        serviceManager.addService(reservation2, airportPickup);

// Display services
        serviceManager.displayServices(reservation1);
        serviceManager.displayServices(reservation2);
        return serviceManager;
    }
}
