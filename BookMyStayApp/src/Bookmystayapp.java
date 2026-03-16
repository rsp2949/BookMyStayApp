/**
 * ============================================================
 * ABSTRACT CLASS - Room
 * ============================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * This abstract class represents a generic hotel room.
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
 * ============================================================
 * CLASS - SingleRoom
 * ============================================================
 */

class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}


/**
 * ============================================================
 * CLASS - DoubleRoom
 * ============================================================
 */

class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}


/**
 * ============================================================
 * CLASS - SuiteRoom
 * ============================================================
 */

class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}


/**
 * ============================================================
 * MAIN CLASS
 * ============================================================
 *
 * Use Case 2: Room Initialization
 *
 * @version 2.1
 */

public class Bookmystayapp {

    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization\n");

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("Single Room:");
        singleRoom.displayRoomDetails();
        System.out.println("Available: " + singleAvailability);

        System.out.println("\nDouble Room:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleAvailability);

        System.out.println("\nSuite Room:");
        suiteRoom.displayRoomDetails();
        System.out.println("Available: " + suiteAvailability);

    }
}