import java.util.*;

/*
 ============================================================
 CLASS - Reservation
 ============================================================
*/
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

/*
 ============================================================
 CLASS - BookingRequestQueue
 ============================================================
*/
class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean hasRequests() {
        return !queue.isEmpty();
    }
}

/*
 ============================================================
 CLASS - RoomInventory
 ============================================================
*/
class RoomInventory {

    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single", 5);
        availability.put("Double", 3);
        availability.put("Suite", 2);
    }

    public Map<String, Integer> getAvailability() {
        return availability;
    }

    public void update(String roomType, int value) {
        availability.put(roomType, value);
    }
}

/*
 ============================================================
 CLASS - RoomAllocationService
 ============================================================
*/
class RoomAllocationService {

    private Map<String, Integer> counter = new HashMap<>();

    public void allocateRoom(Reservation r, RoomInventory inventory) {

        String type = r.getRoomType();
        Map<String, Integer> avail = inventory.getAvailability();

        if (!avail.containsKey(type) || avail.get(type) <= 0) {
            System.out.println("No rooms available for " + type);
            return;
        }

        int id = counter.getOrDefault(type, 0) + 1;
        counter.put(type, id);

        String roomId = type + "-" + id;

        inventory.update(type, avail.get(type) - 1);

        System.out.println("Booking confirmed for Guest: "
                + r.getGuestName() + ", Room ID: " + roomId);
    }
}

/*
 ============================================================
 CLASS - ConcurrentBookingProcessor
 ============================================================
*/
class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService
    ) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {

        while (true) {

            Reservation reservation;

            // CRITICAL SECTION 1 → Queue access
            synchronized (bookingQueue) {
                if (!bookingQueue.hasRequests()) {
                    break;
                }
                reservation = bookingQueue.getNextRequest();
            }

            // CRITICAL SECTION 2 → Inventory update
            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}

/*
 ============================================================
 MAIN CLASS - UC11
 ============================================================
*/
public class Bookmystayapp {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation\n");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Add requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));

        // Create threads
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        // Start threads
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // Final inventory
        System.out.println("\nRemaining Inventory:");
        System.out.println("Single: " + inventory.getAvailability().get("Single"));
        System.out.println("Double: " + inventory.getAvailability().get("Double"));
        System.out.println("Suite: " + inventory.getAvailability().get("Suite"));
    }
}