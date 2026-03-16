import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * ABSTRACT CLASS - Room
 * ============================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * @version 2.1
 */
abstract class Room {

    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}


/**
 * CLASS - SingleRoom
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}


/**
 * CLASS - DoubleRoom
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}


/**
 * CLASS - SuiteRoom
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}


/**
 * ============================================================
 * CLASS - RoomInventory
 * ============================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * @version 3.1
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {

        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);

    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

}


/**
 * ============================================================
 * MAIN CLASS
 * ============================================================
 *
 * Use Case 3: Inventory Setup
 *
 * @version 3.1
 */
public class Bookmystayapp {

    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status\n");

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();

        System.out.println("Single Room:");
        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + inventory.getRoomAvailability().get("Single"));

        System.out.println("\nDouble Room:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + inventory.getRoomAvailability().get("Double"));

        System.out.println("\nSuite Room:");
        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + inventory.getRoomAvailability().get("Suite"));

    }
}