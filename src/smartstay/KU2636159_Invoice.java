
package smartstay;

import java.math.BigDecimal;
import java.time.LocalDate;

// Represents the detailed invoice generated for a hotel reservation.
public class KU2636159_Invoice {

    // Unique identifier assigned to the invoice.
    private String invoiceId;

    // Booking reference associated with the invoice.
    private String bookingId;

    // Room allocated to the guest.
    private String roomId;

    // Category of the reserved room.
    private KU2636159_RoomType roomType;

    // Bed type available in the room.
    private KU2636159_BedType bedType;

    // Guest's full name.
    private String guestName;

    // Guest's email address.
    private String guestEmail;

    // Guest's contact number.
    private String guestContact;

    // Reservation check-in and check-out dates.
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    // Number of nights and guests included in the booking.
    private int numberOfNights;
    private int guestCount;

    // Daily room rate and calculated charges.
    private BigDecimal dailyRoomRate;
    private BigDecimal baseRoomCharge;
    private BigDecimal extraGuestCharge;
    private BigDecimal discountAmount;
    private BigDecimal taxAmount;
    private BigDecimal advanceDeducted;
    private BigDecimal finalPayableAmount;

    // Creates an invoice using the calculated reservation details.
    public KU2636159_Invoice(
            String invoiceId,
            String bookingId,
            String roomId,
            KU2636159_RoomType roomType,
            KU2636159_BedType bedType,
            String guestName,
            String guestEmail,
            String guestContact,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            int numberOfNights,
            int guestCount,
            BigDecimal dailyRoomRate,
            BigDecimal baseRoomCharge,
            BigDecimal extraGuestCharge,
            BigDecimal discountAmount,
            BigDecimal taxAmount,
            BigDecimal advanceDeducted,
            BigDecimal finalPayableAmount) {

        this.invoiceId = invoiceId;
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.roomType = roomType;
        this.bedType = bedType;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.guestContact = guestContact;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfNights = numberOfNights;
        this.guestCount = guestCount;
        this.dailyRoomRate = dailyRoomRate;
        this.baseRoomCharge = baseRoomCharge;
        this.extraGuestCharge = extraGuestCharge;
        this.discountAmount = discountAmount;
        this.taxAmount = taxAmount;
        this.advanceDeducted = advanceDeducted;
        this.finalPayableAmount = finalPayableAmount;
    }

    // Generates the invoice details as formatted text.
    public String generateInvoice() {
        return "SMARTSTAY HOTEL BOOKING INVOICE"
                + "\n================================"
                + "\nInvoice ID: " + invoiceId
                + "\nBooking ID: " + bookingId
                + "\nRoom ID: " + roomId
                + "\nRoom Type: " + roomType
                + "\nBed Type: " + bedType
                + "\nGuest Name: " + guestName
                + "\nGuest Email: " + guestEmail
                + "\nGuest Contact: " + guestContact
                + "\nCheck-in Date: " + checkInDate
                + "\nCheck-out Date: " + checkOutDate
                + "\nNumber of Nights: " + numberOfNights
                + "\nGuest Count: " + guestCount
                + "\nDaily Room Rate: LKR " + dailyRoomRate
                + "\nBase Room Charge: LKR " + baseRoomCharge
                + "\nExtra Guest Charge: LKR " + extraGuestCharge
                + "\nDiscount: LKR " + discountAmount
                + "\nTax: LKR " + taxAmount
                + "\nAdvance Deducted: LKR " + advanceDeducted
                + "\nFinal Payable Amount: LKR " + finalPayableAmount
                + "\n================================";
    }

    // Displays the generated invoice in the console.
    public void displayInvoice() {
        System.out.println(generateInvoice());
    }

    // Returns the final amount payable by the guest.
    public BigDecimal getFinalPayableAmount() {
        return finalPayableAmount;
    }

    // Returns the room ID associated with the invoice.
    public String getRoomId() {
        return roomId;
    }
}
