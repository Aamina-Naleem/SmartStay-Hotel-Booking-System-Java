
package smartstay;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

// Main application class for the SmartStay Hotel Booking System.
public class KU2636159_SmartStayApp {

    // Manages registered guests.
    private KU2636159_GuestManager guestManager;

    // Manages hotel rooms.
    private KU2636159_RoomManager roomManager;

    // Manages room reservations.
    private KU2636159_ReservationManager reservationManager;

    // Handles booking charges and invoice generation.
    private KU2636159_BillingService billingService;

    // Handles administrator room-management operations.
    private KU2636159_Admin admin;

    // Reads input from the keyboard.
    private Scanner scanner;

    // Generates unique booking IDs during the application session.
    private int bookingCounter = 1;

    // Creates and connects all application components.
    public KU2636159_SmartStayApp() {

        scanner = new Scanner(System.in);

        guestManager = new KU2636159_GuestManager();

        roomManager = new KU2636159_RoomManager();

        billingService = new KU2636159_BillingService();

        reservationManager = new KU2636159_ReservationManager(
                roomManager,
                billingService
        );

        admin = new KU2636159_Admin(
                "ADM001",
                "admin",
                "admin"
        );
    }

    // Starts the SmartStay application.
    public void startApplication() {

        System.out.println("=========================================");
        System.out.println("      SMARTSTAY HOTEL BOOKING SYSTEM");
        System.out.println("=========================================");

        displayMainMenu();
    }

    // Displays the main application menu.
    public void displayMainMenu() {

        boolean running = true;

        while (running) {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Guest Management");
            System.out.println("2. Room Management");
            System.out.println("3. Reservation Management");
            System.out.println("4. View All Reservations");
            System.out.println("5. Exit");
            System.out.println("===============================");

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    displayGuestMenu();
                    break;

                case 2:
                    displayRoomMenu();
                    break;

                case 3:
                    displayReservationMenu();
                    break;

                case 4:
                    viewAllReservations();
                    break;

                case 5:
                    shutdownApplication();
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Displays the guest management menu.
    private void displayGuestMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n======= GUEST MANAGEMENT =======");
            System.out.println("1. Register Guest");
            System.out.println("2. Search Guest by Name");
            System.out.println("3. View All Guests");
            System.out.println("4. Update Guest Contact Details");
            System.out.println("5. Back to Main Menu");
            System.out.println("================================");

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerGuest();
                    break;

                case 2:
                    searchGuest();
                    break;

                case 3:
                    viewAllGuests();
                    break;

                case 4:
                    updateGuest();
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Registers a new guest.
    private void registerGuest() {

        System.out.println("\n--- Guest Registration ---");

        System.out.print("Enter Guest ID: ");
        String guestId = scanner.nextLine().trim();

        System.out.print("Enter NIC/Passport Number: ");
        String nic = scanner.nextLine().trim();

        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine().trim();

        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine().trim();

        if (guestId.isEmpty() || nic.isEmpty() || name.isEmpty()
                || contact.isEmpty() || email.isEmpty()) {

            System.out.println("All guest details are required.");
            return;
        }

        KU2636159_Guest guest = new KU2636159_Guest(
                guestId, nic, name, contact, email
        );

        if (guestManager.registerGuest(guest)) {
            System.out.println("Guest registration completed.");
        } else {
            System.out.println("Registration failed. Guest ID may already exist.");
        }
    }

    // Searches for guests by their full name.
    private void searchGuest() {

        System.out.print("Enter guest name to search: ");
        String name = scanner.nextLine().trim();

        List<KU2636159_Guest> results =
                guestManager.searchGuestsByName(name);

        if (results.isEmpty()) {
            System.out.println("No matching guests found.");
            return;
        }

        for (KU2636159_Guest guest : results) {
            System.out.println("\n" + guest.getGuestDetails());
            System.out.println("-----------------------------");
        }
    }

    // Displays all registered guests.
    private void viewAllGuests() {

        List<KU2636159_Guest> guests =
                guestManager.getAllGuests();

        if (guests.isEmpty()) {
            System.out.println("No guests have been registered.");
            return;
        }

        System.out.println("\n--- Registered Guests ---");

        for (KU2636159_Guest guest : guests) {
            System.out.println("\n" + guest.getGuestDetails());
            System.out.println("-----------------------------");
        }
    }

    // Updates a guest's contact information.
    private void updateGuest() {

        System.out.print("Enter Guest ID: ");
        String guestId = scanner.nextLine().trim();

        System.out.print("Enter new Contact Number: ");
        String contact = scanner.nextLine().trim();

        System.out.print("Enter new Email Address: ");
        String email = scanner.nextLine().trim();

        if (contact.isEmpty() || email.isEmpty()) {
            System.out.println("Contact number and email are required.");
            return;
        }

        if (guestManager.updateGuest(guestId, contact, email)) {
            System.out.println("Guest details updated successfully.");
        } else {
            System.out.println("Guest not found.");
        }
    }

    // Displays the room management menu.
    private void displayRoomMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n======= ROOM MANAGEMENT =======");
            System.out.println("1. Add Room");
            System.out.println("2. Update Room Package");
            System.out.println("3. Remove Room");
            System.out.println("4. Manage Room Availability");
            System.out.println("5. Check Room Availability");
            System.out.println("6. View All Rooms");
            System.out.println("7. Back to Main Menu");
            System.out.println("================================");

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addRoom();
                    break;

                case 2:
                    updateRoomPackage();
                    break;

                case 3:
                    removeRoom();
                    break;

                case 4:
                    manageRoomAvailability();
                    break;

                case 5:
                    checkRoomAvailability();
                    break;

                case 6:
                    admin.viewAllRooms(roomManager);
                    break;

                case 7:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Adds a new room to the system.
    private void addRoom() {

        System.out.println("\n--- Add New Room ---");

        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine().trim();

        if (roomId.isEmpty()) {
            System.out.println("Room ID is required.");
            return;
        }

        KU2636159_RoomPackage roomPackage = selectRoomPackage();

        if (roomPackage == null) {
            return;
        }

        KU2636159_BedType bedType = selectBedType();

        if (bedType == null) {
            return;
        }

        KU2636159_Room room = new KU2636159_Room(
                roomId,
                bedType,
                KU2636159_AvailabilityStatus.AVAILABLE,
                roomPackage
        );

        if (admin.addRoom(roomManager, room)) {
            System.out.println("Room added successfully.");
        } else {
            System.out.println("Room could not be added. Check the room ID.");
        }
    }

    // Selects a room package.
    private KU2636159_RoomPackage selectRoomPackage() {

        System.out.println("\nSelect Room Type:");
        System.out.println("1. Standard");
        System.out.println("2. Deluxe");
        System.out.println("3. Suite");
        System.out.println("4. Family Suite");

        int choice = readInt("Enter room type: ");

        switch (choice) {

            case 1:
                return new KU2636159_StandardPackage();

            case 2:
                return new KU2636159_DeluxePackage();

            case 3:
                return new KU2636159_SuitePackage();

            case 4:
                return new KU2636159_FamilySuitePackage();

            default:
                System.out.println("Invalid room type.");
                return null;
        }
    }

    // Selects a bed type.
    private KU2636159_BedType selectBedType() {

        System.out.println("\nSelect Bed Type:");
        System.out.println("1. Single");
        System.out.println("2. Double");
        System.out.println("3. Queen");
        System.out.println("4. King");

        int choice = readInt("Enter bed type: ");

        switch (choice) {

            case 1:
                return KU2636159_BedType.SINGLE;

            case 2:
                return KU2636159_BedType.DOUBLE;

            case 3:
                return KU2636159_BedType.QUEEN;

            case 4:
                return KU2636159_BedType.KING;

            default:
                System.out.println("Invalid bed type.");
                return null;
        }
    }

    // Updates the package assigned to an existing room.
    private void updateRoomPackage() {

        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine().trim();

        KU2636159_Room room =
                roomManager.findRoomById(roomId);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        KU2636159_RoomPackage updatedPackage =
                selectRoomPackage();

        if (updatedPackage == null) {
            return;
        }

        room.setRoomPackage(updatedPackage);

        System.out.println("Room package updated successfully.");
    }

    // Removes a room from the system.
    private void removeRoom() {

        System.out.print("Enter Room ID to remove: ");
        String roomId = scanner.nextLine().trim();

        if (admin.removeRoom(roomManager, roomId)) {
            System.out.println("Room removed successfully.");
        } else {
            System.out.println("Room not found.");
        }
    }

    // Updates the availability status of a room.
    private void manageRoomAvailability() {

        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine().trim();

        System.out.println("\nSelect Availability Status:");
        System.out.println("1. Available");
        System.out.println("2. Reserved");
        System.out.println("3. Under Maintenance");

        int choice = readInt("Enter status: ");

        KU2636159_AvailabilityStatus status;

        switch (choice) {

            case 1:
                status = KU2636159_AvailabilityStatus.AVAILABLE;
                break;

            case 2:
                status = KU2636159_AvailabilityStatus.RESERVED;
                break;

            case 3:
                status = KU2636159_AvailabilityStatus.UNDER_MAINTENANCE;
                break;

            default:
                System.out.println("Invalid availability status.");
                return;
        }

        if (admin.manageRoomAvailability(
                roomManager, roomId, status)) {

            System.out.println("Room availability updated.");
        } else {
            System.out.println("Room not found.");
        }
    }

    // Checks the current availability status of a room.
    private void checkRoomAvailability() {

        System.out.println("\n--- Check Room Availability ---");

        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine().trim();

        KU2636159_Room room =
                roomManager.findRoomById(roomId);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.println("\n--- Room Availability Details ---");
        System.out.println("Room ID: " + room.getRoomId());
        System.out.println("Room Type: " + room.getRoomType());
        System.out.println("Bed Type: " + room.getBedType());
        System.out.println("Daily Rate: LKR " + room.getDailyRate());
        System.out.println("Availability Status: "
                + room.getAvailabilityStatus());

        if (room.isAvailable()) {
            System.out.println("Result: Room is currently available.");
        } else {
            System.out.println("Result: Room is currently not available.");
        }
    }

    // Displays the reservation management menu.
    private void displayReservationMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n===== RESERVATION MANAGEMENT =====");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. Search Reservation by Booking ID");
            System.out.println("4. Search Reservations by Guest Name");
            System.out.println("5. View Reservations by Check-in Date");
            System.out.println("6. Cancel Reservation");
            System.out.println("7. Back to Main Menu");
            System.out.println("==================================");

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    searchAvailableRooms();
                    break;

                case 2:
                    makeReservation();
                    break;

                case 3:
                    searchReservation();
                    break;

                case 4:
                    searchReservationsByGuest();
                    break;

                case 5:
                    viewReservationsByDate();
                    break;

                case 6:
                    cancelReservation();
                    break;

                case 7:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Searches for available rooms by type and dates.
    private void searchAvailableRooms() {

        KU2636159_RoomPackage selectedPackage =
                selectRoomPackage();

        if (selectedPackage == null) {
            return;
        }

        LocalDate checkIn = readDate("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkOut = readDate("Enter check-out date (YYYY-MM-DD): ");

        if (checkIn == null || checkOut == null
                || !checkOut.isAfter(checkIn)) {

            System.out.println("Please enter valid booking dates.");
            return;
        }

        List<KU2636159_Room> results =
                roomManager.searchAvailableRooms(
                        selectedPackage.getRoomType(),
                        checkIn,
                        checkOut
                );

        if (results.isEmpty()) {
            System.out.println("No available rooms found.");
            return;
        }

        System.out.println("\n--- Available Rooms ---");

        for (KU2636159_Room room : results) {

            System.out.println(
                    "Room ID: " + room.getRoomId()
                    + "\nRoom Type: " + room.getRoomType()
                    + "\nBed Type: " + room.getBedType()
                    + "\nDaily Rate: LKR " + room.getDailyRate()
                    + "\n-----------------------------"
            );
        }
    }

    // Creates a new room reservation.
    private void makeReservation() {

        System.out.println("\n--- Make Reservation ---");

        System.out.print("Enter Guest ID: ");
        String guestId = scanner.nextLine().trim();

        KU2636159_Guest guest =
                guestManager.findGuestById(guestId);

        if (guest == null) {
            System.out.println("Guest not found. Register the guest first.");
            return;
        }

        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine().trim();

        LocalDate checkIn = readDate("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkOut = readDate("Enter check-out date (YYYY-MM-DD): ");

        if (checkIn == null || checkOut == null) {
            System.out.println("Invalid booking dates.");
            return;
        }

        int guestCount = readInt("Enter number of guests: ");

        String bookingId = String.format(
                "SSB%05d", bookingCounter
        );

        try {

            KU2636159_Reservation reservation =
                    reservationManager.createReservation(
                            bookingId,
                            guest,
                            roomId,
                            checkIn,
                            checkOut,
                            guestCount
                    );

            bookingCounter++;

            System.out.println("\nReservation created successfully.");
            System.out.println(reservation.getBookingDetails());

            if (reservation.getInvoice() != null) {
                reservation.getInvoice().displayInvoice();
            }

        } catch (KU2636159_BookingRuleException exception) {

            System.out.println(
                    "Reservation failed: " + exception.getMessage()
            );
        }
    }

    // Searches for a reservation using its booking ID.
    private void searchReservation() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine().trim();

        KU2636159_Reservation reservation =
                reservationManager.searchReservationById(bookingId);

        if (reservation == null) {
            System.out.println("Reservation not found.");
            return;
        }

        System.out.println("\n" + reservation.getBookingDetails());

        if (reservation.getInvoice() != null) {
            reservation.getInvoice().displayInvoice();
        }
    }

    // Searches reservations using a guest's name.
    private void searchReservationsByGuest() {

        System.out.print("Enter Guest Name: ");
        String guestName = scanner.nextLine().trim();

        List<KU2636159_Reservation> results =
                reservationManager.searchReservationsByGuestName(
                        guestName
                );

        if (results.isEmpty()) {
            System.out.println("No matching reservations found.");
            return;
        }

        for (KU2636159_Reservation reservation : results) {
            System.out.println("\n" + reservation.getBookingDetails());
            System.out.println("-----------------------------");
        }
    }

    // Displays reservations for a selected check-in date.
    private void viewReservationsByDate() {

        LocalDate date =
                readDate("Enter check-in date (YYYY-MM-DD): ");

        if (date == null) {
            System.out.println("Invalid date.");
            return;
        }

        List<KU2636159_Reservation> results =
                reservationManager.getReservationsByCheckInDate(date);

        if (results.isEmpty()) {
            System.out.println("No reservations found for this date.");
            return;
        }

        for (KU2636159_Reservation reservation : results) {
            System.out.println("\n" + reservation.getBookingDetails());
            System.out.println("-----------------------------");
        }
    }

    // Cancels a reservation using its booking ID.
    private void cancelReservation() {

        System.out.print("Enter Booking ID to cancel: ");
        String bookingId = scanner.nextLine().trim();

        try {

            if (reservationManager.cancelReservation(bookingId)) {
                System.out.println("Reservation cancelled successfully.");
                System.out.println("The refundable booking advance has been processed.");
            }

        } catch (KU2636159_BookingRuleException exception) {

            System.out.println(
                    "Cancellation failed: " + exception.getMessage()
            );
        }
    }

    // Displays all reservations.
    private void viewAllReservations() {

        List<KU2636159_Reservation> reservations =
                reservationManager.getAllReservations();

        if (reservations.isEmpty()) {
            System.out.println("\nNo reservations have been made.");
            return;
        }

        System.out.println("\n======= ALL RESERVATIONS =======");

        for (KU2636159_Reservation reservation : reservations) {

            System.out.println("\n" + reservation.getBookingDetails());
            System.out.println("-----------------------------");
        }
    }

    // Reads a date in YYYY-MM-DD format.
    private LocalDate readDate(String message) {

        System.out.print(message);
        String input = scanner.nextLine().trim();

        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException exception) {
            return null;
        }
    }

    // Reads an integer safely from keyboard input.
    private int readInt(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Shuts down the application.
    public void shutdownApplication() {

        System.out.println("\nSmartStay application has been closed.");

        scanner.close();
    }

    // Main method that runs the application.
    public static void main(String[] args) {

        KU2636159_SmartStayApp application =
                new KU2636159_SmartStayApp();

        application.startApplication();
    }
}
