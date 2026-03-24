import java.util.*;

/*
 ============================================================
 CLASS - Service
 ============================================================
*/
class Service {

    private String serviceName;
    private double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

/*
 ============================================================
 CLASS - AddOnServiceManager
 ============================================================
*/
class AddOnServiceManager {

    private Map<String, List<Service>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, Service service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {

        List<Service> services = servicesByReservation.getOrDefault(reservationId, new ArrayList<>());

        double total = 0;

        for (Service s : services) {
            total += s.getCost();
        }

        return total;
    }
}

/*
 ============================================================
 MAIN CLASS - UC7
 ============================================================
*/
public class Bookmystayapp {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection\n");

        String reservationId = "Single-1";

        AddOnServiceManager manager = new AddOnServiceManager();

        Service breakfast = new Service("Breakfast", 500);
        Service spa = new Service("Spa", 1000);

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);

        double total = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + total);
    }
}