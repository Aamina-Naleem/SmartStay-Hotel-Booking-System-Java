
package smartstay;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

// Manages all room reservations in the SmartStay system.
public class KU2636159_ReservationManager {

    // Stores all reservations created in the system.
    private List<KU2636159_Reservation> reservations;

    // Provides access to hotel room records.
    private KU2636159_RoomManager roomManager;

    // Handles reservation billing and invoice generation.
    private KU2636159_BillingService billingService;

    // Creates the reservation manager with its required services.
    public KU2636159_ReservationManager(
            KU2636159_RoomManager roomManager,
            KU2636159_BillingService billingService) {

        this.reservations = new ArrayList<>();
        this.roomManager = roomManager;
        this.billingService = billingService;
    }

    // Creates a reservation after checking the booking requirements.
    public KU2636159_Reservation createReservation(
            String bookingId,
            KU2636159_Guest guest,
            String roomId,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            int guestCount) throws KU2636159_BookingRuleException {

        // Check that the selected room exists.
        KU2636159_Room room = roomManager.findRoomById(roomId);

        if (room == null) {
            throw new KU2636159_BookingRuleException(
                    "The selected room does not exist.");
        }

        // Check that the guest is registered.
        if (guest == null) {
            throw new KU2636159_BookingRuleException(
                    "A registered guest is required.");
        }

        // Check that the room is currently available.
        if (!room.isAvailable()) {
            throw new KU2636159_BookingRuleException(
                    "The selected room is not available.");
        }

        // Validate the booking dates.
        if (checkInDate == null || checkOutDate == null
                || !checkOutDate.isAfter(checkInDate)) {
            throw new KU2636159_BookingRuleException(
                    "Please enter valid check-in and check-out dates.");
        }

        // Ensure bookings are made at least two days in advance.
        if (checkInDate.isBefore(LocalDate.now().plusDays(2))) {
            throw new KU2636159_BookingRuleException(
                    "Bookings must be made at least two days in advance.");
        }

        // Ensure the guest count is valid.
        if (guestCount <= 0) {
            throw new KU2636159_BookingRuleException(
                    "At least one guest is required.");
        }

        // Check whether the room is already reserved for overlapping dates.
        for (KU2636159_Reservation existing : reservations) {

            if (existing.getReservationStatus()
                    == KU2636159_ReservationStatus.CONFIRMED
                    && existing.getCheckInDate().isBefore(checkOutDate)
                    && existing.getCheckOutDate().isAfter(checkInDate)) {

                // Check whether the existing reservation uses the same room.
                KU2636159_Room existingRoom = existing.getRoom();

                if (existingRoom != null
                        && roomId.equalsIgnoreCase(
                                existingRoom.getRoomId())) {

                    throw new KU2636159_BookingRuleException(
                            "This room is already reserved for the selected dates.");
                }
            }
        }

        // Create the refundable booking advance.
        KU2636159_BookingAdvance advance =
                new KU2636159_BookingAdvance();

        // Process the advance payment.
        advance.processAdvancePayment();

        // Create the reservation with its assigned room.
        KU2636159_Reservation reservation =
                new KU2636159_Reservation(
                        bookingId,
                        guest,
                        room,
                        checkInDate,
                        checkOutDate,
                        guestCount,
                        advance
                );

        // Generate and attach the invoice.
        KU2636159_Invoice invoice =
                billingService.generateInvoice(
                        "INV-" + bookingId,
                        reservation,
                        room
                );

        reservation.setInvoice(invoice);

        // Add the reservation to the collection.
        reservations.add(reservation);

        // Mark the room as reserved.
        room.setAvailabilityStatus(
                KU2636159_AvailabilityStatus.RESERVED);

        return reservation;
    }

    // Searches for a reservation using its booking ID.
    public KU2636159_Reservation searchReservationById(
            String bookingId) {

        for (KU2636159_Reservation reservation : reservations) {

            if (reservation.getBookingId()
                    .equalsIgnoreCase(bookingId)) {
                return reservation;
            }
        }

        return null;
    }

    // Searches reservations using the guest's name.
    public List<KU2636159_Reservation> searchReservationsByGuestName(
            String guestName) {

        List<KU2636159_Reservation> matchingReservations =
                new ArrayList<>();

        for (KU2636159_Reservation reservation : reservations) {

            if (reservation.getGuest().getFullName()
                    .toLowerCase().contains(guestName.toLowerCase())) {

                matchingReservations.add(reservation);
            }
        }

        return matchingReservations;
    }

    // Retrieves reservations matching a particular check-in date.
    public List<KU2636159_Reservation> getReservationsByCheckInDate(
            LocalDate checkInDate) {

        List<KU2636159_Reservation> matchingReservations =
                new ArrayList<>();

        for (KU2636159_Reservation reservation : reservations) {

            if (reservation.getCheckInDate().equals(checkInDate)) {
                matchingReservations.add(reservation);
            }
        }

        return matchingReservations;
    }

    // Cancels a reservation when the 24-hour restriction allows it.
    public boolean cancelReservation(String bookingId)
            throws KU2636159_BookingRuleException {

        KU2636159_Reservation reservation =
                searchReservationById(bookingId);

        if (reservation == null) {
            throw new KU2636159_BookingRuleException(
                    "Reservation not found.");
        }

        if (reservation.getReservationStatus()
                == KU2636159_ReservationStatus.CANCELLED) {
            throw new KU2636159_BookingRuleException(
                    "This reservation has already been cancelled.");
        }

        // Calculate the remaining time before check-in.
        long hoursUntilCheckIn = ChronoUnit.HOURS.between(
                java.time.LocalDateTime.now(),
                reservation.getCheckInDate().atStartOfDay());

        // Prevent cancellation within 24 hours of check-in.
        if (hoursUntilCheckIn < 24) {
            throw new KU2636159_BookingRuleException(
                    "Reservations cannot be cancelled within 24 hours of check-in.");
        }

        // Cancel the reservation.
        reservation.cancelReservation();

        // Refund the refundable booking advance.
        reservation.getBookingAdvance().refundAdvance();

        // Check whether another confirmed reservation uses the same room.
        KU2636159_Room room = reservation.getRoom();
        boolean anotherActiveReservation = false;

        for (KU2636159_Reservation existing : reservations) {

            if (existing != reservation
                    && existing.getReservationStatus()
                    == KU2636159_ReservationStatus.CONFIRMED
                    && existing.getRoom() != null
                    && existing.getRoom().getRoomId()
                    .equalsIgnoreCase(room.getRoomId())) {

                anotherActiveReservation = true;
                break;
            }
        }

        // Restore availability only when no other active booking exists.
        if (!anotherActiveReservation && room != null) {
            room.setAvailabilityStatus(
                    KU2636159_AvailabilityStatus.AVAILABLE);
        }

        return true;
    }

    // Returns all reservations in the system.
    public List<KU2636159_Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }
}
