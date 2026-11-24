
package smartstay;

import java.math.BigDecimal;

// Represents the refundable booking advance paid during reservation.
public class KU2636159_BookingAdvance {

    // Booking advance amount.
    private BigDecimal amount;

    // Current payment status of the advance.
    private KU2636159_PaymentStatus paymentStatus;

    // Indicates whether the advance can be refunded.
    private boolean refundable;

    // Creates a booking advance with the required amount.
    public KU2636159_BookingAdvance() {
        this.amount = new BigDecimal("10000.00");
        this.paymentStatus = KU2636159_PaymentStatus.PENDING;
        this.refundable = true;
    }

    // Processes the booking advance payment.
    public void processAdvancePayment() {
        paymentStatus = KU2636159_PaymentStatus.PAID;
    }

    // Refunds the booking advance when eligible.
    public void refundAdvance() {

        if (refundable &&
                paymentStatus == KU2636159_PaymentStatus.PAID) {

            paymentStatus = KU2636159_PaymentStatus.REFUND;
        }
    }

    // Returns the booking advance amount.
    public BigDecimal getAmount() {
        return amount;
    }

    // Returns the current payment status.
    public KU2636159_PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    // Returns whether the advance is refundable.
    public boolean isRefundable() {
        return refundable;
    }
}
