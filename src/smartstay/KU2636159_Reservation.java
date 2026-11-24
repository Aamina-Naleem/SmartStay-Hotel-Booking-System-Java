
package smartstay;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Represents a guest's room reservation in the SmartStay system.
public class KU2636159_Reservation {

    // Unique identifier assigned to the reservation.
    private String bookingId;

    // Guest associated with this reservation.
    private KU2636159_Guest guest;

    // Room assigned to this reservation.
    private KU2636159_Room room;

    // Date on which the guest checks into the hotel.
    private LocalDate checkInDate;

    // Date on which the guest checks out of the hotel.
    private LocalDate checkOutDate;

    // Total number of guests included in the reservation.
    private int guestCount;

    // Current status of the reservation.
    private KU2636159_ReservationStatus reservationStatus;

    // Booking advance associated with the reservation.
    private KU2636159_BookingAdvance bookingAdvance;

    // Invoice generated for the reservation.
    private KU2636159_Invoice invoice;

    // Creates a reservation using the guest, room and booking details.
    public KU2636159_Reservation(
            String bookingId,
            KU2636159_Guest guest,
            KU2636159_Room room,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            int guestCount,
            KU2636159_BookingAdvance bookingAdvance) {

        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestCount = guestCount;
        this.bookingAdvance = bookingAdvance;
        this.reservationStatus =
                KU2636159_ReservationStatus.CONFIRMED;
        this.invoice = null;
    }

    // Calculates the number of nights between check-in and check-out.
    public int calculateNights() {
        return (int) ChronoUnit.DAYS.between(
                checkInDate, checkOutDate);
    }

    // Returns the main details of the reservation.
    public String getBookingDetails() {
        return "Booking ID: " + bookingId
                + "\nGuest Name: " + guest.getFullName()
                + "\nRoom ID: " + room.getRoomId()
                + "\nCheck-in Date: " + checkInDate
                + "\nCheck-out Date: " + checkOutDate
                + "\nNumber of Nights: " + calculateNights()
                + "\nGuest Count: " + guestCount
                + "\nReservation Status: " + reservationStatus;
    }

    // Confirms the reservation.
    public void confirmReservation() {
        reservationStatus =
                KU2636159_ReservationStatus.CONFIRMED;
    }

    // Cancels the reservation.
    public void cancelReservation() {
        reservationStatus =
                KU2636159_ReservationStatus.CANCELLED;
    }

    // Returns the current reservation status.
    public KU2636159_ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    // Returns the unique booking ID.
    public String getBookingId() {
        return bookingId;
    }

    // Returns the guest associated with this reservation.
    public KU2636159_Guest getGuest() {
        return guest;
    }

    // Returns the room assigned to this reservation.
    public KU2636159_Room getRoom() {
        return room;
    }

    // Returns the check-in date.
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    // Returns the check-out date.
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    // Returns the number of guests.
    public int getGuestCount() {
        return guestCount;
    }

    // Returns the booking advance.
    public KU2636159_BookingAdvance getBookingAdvance() {
        return bookingAdvance;
    }

    // Returns the invoice associated with the reservation.
    public KU2636159_Invoice getInvoice() {
        return invoice;
    }

    // Assigns an invoice to the reservation.
    public void setInvoice(KU2636159_Invoice invoice) {
        this.invoice = invoice;
    }
}
