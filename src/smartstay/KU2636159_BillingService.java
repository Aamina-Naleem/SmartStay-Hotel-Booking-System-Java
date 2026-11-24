
package smartstay;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Handles all room booking and invoice calculations.
public class KU2636159_BillingService {

    // Discount rate for reservations of five or more nights.
    private BigDecimal discountRate;

    // Minimum number of nights required for a discount.
    private int discountMinimumNights;

    // Initialises the billing service with the coursework discount rule.
    public KU2636159_BillingService() {
        discountRate = new BigDecimal("0.12");
        discountMinimumNights = 5;
    }

    // Calculates the basic room charge before additional charges.
    public BigDecimal calculateBaseCharge(
            BigDecimal dailyRate, int numberOfNights) {

        return dailyRate.multiply(
                BigDecimal.valueOf(numberOfNights));
    }

    // Calculates the additional charge for guests exceeding room capacity.
    public BigDecimal calculateExtraGuestCharge(
            KU2636159_Room room,
            int guestCount,
            int numberOfNights) {

        return room.getRoomPackage()
                .calculateExtraGuestCharge(guestCount)
                .multiply(BigDecimal.valueOf(numberOfNights));
    }

    // Calculates the discount for eligible reservations.
    public BigDecimal calculateDiscount(
            BigDecimal baseRoomCharge,
            int numberOfNights) {

        if (numberOfNights >= discountMinimumNights) {
            return baseRoomCharge.multiply(discountRate)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        return BigDecimal.ZERO;
    }

    // Calculates tax after applying the eligible discount.
    public BigDecimal calculateTax(
            KU2636159_Room room,
            BigDecimal baseRoomCharge,
            BigDecimal extraGuestCharge,
            BigDecimal discountAmount) {

        BigDecimal taxableAmount = baseRoomCharge
                .add(extraGuestCharge)
                .subtract(discountAmount);

        return room.getRoomPackage()
                .calculateTax(taxableAmount)
                .setScale(2, RoundingMode.HALF_UP);
    }

    // Calculates the final amount after deducting the booking advance.
    public BigDecimal calculateFinalAmount(
            BigDecimal baseRoomCharge,
            BigDecimal extraGuestCharge,
            BigDecimal discountAmount,
            BigDecimal taxAmount,
            BigDecimal advanceDeducted) {

        return baseRoomCharge
                .add(extraGuestCharge)
                .subtract(discountAmount)
                .add(taxAmount)
                .subtract(advanceDeducted)
                .max(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }

    // Generates an invoice containing all reservation and payment details.
    public KU2636159_Invoice generateInvoice(
            String invoiceId,
            KU2636159_Reservation reservation,
            KU2636159_Room room) {

        int nights = reservation.calculateNights();

        BigDecimal dailyRate = room.getDailyRate();

        BigDecimal baseCharge = calculateBaseCharge(
                dailyRate, nights);

        BigDecimal extraCharge = calculateExtraGuestCharge(
                room, reservation.getGuestCount(), nights);

        BigDecimal discount = calculateDiscount(
                baseCharge, nights);

        BigDecimal tax = calculateTax(
                room, baseCharge, extraCharge, discount);

        BigDecimal advance = reservation.getBookingAdvance()
                .getAmount();

        BigDecimal advanceDeducted = BigDecimal.ZERO;

        if (reservation.getBookingAdvance().getPaymentStatus()
                == KU2636159_PaymentStatus.PAID) {
            advanceDeducted = advance;
        }

        BigDecimal finalAmount = calculateFinalAmount(
                baseCharge,
                extraCharge,
                discount,
                tax,
                advanceDeducted);

        KU2636159_Guest guest = reservation.getGuest();

        return new KU2636159_Invoice(
                invoiceId,
                reservation.getBookingId(),
                room.getRoomId(),
                room.getRoomType(),
                room.getBedType(),
                guest.getFullName(),
                guest.getEmailAddress(),
                guest.getContactNumber(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate(),
                nights,
                reservation.getGuestCount(),
                dailyRate,
                baseCharge,
                extraCharge,
                discount,
                tax,
                advanceDeducted,
                finalAmount
        );
    }
}
