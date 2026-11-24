
package smartstay;

import java.math.BigDecimal;

// Abstract parent class representing the different room packages.
public abstract class KU2636159_RoomPackage {

    // Room category associated with the package.
    protected KU2636159_RoomType roomType;

    // Daily price charged for the room.
    protected BigDecimal dailyRate;

    // Indicates whether complimentary breakfast is included.
    protected boolean breakfastIncluded;

    // Additional charge for each extra guest.
    protected BigDecimal extraGuestCharge;

    // Tax rate applicable to the room package.
    protected BigDecimal taxRate;

    // Maximum number of guests allowed in the room.
    protected int roomCapacity;

    // Initialises the details of a room package.
    public KU2636159_RoomPackage(
            KU2636159_RoomType roomType,
            BigDecimal dailyRate,
            boolean breakfastIncluded,
            BigDecimal extraGuestCharge,
            BigDecimal taxRate,
            int roomCapacity) {

        this.roomType = roomType;
        this.dailyRate = dailyRate;
        this.breakfastIncluded = breakfastIncluded;
        this.extraGuestCharge = extraGuestCharge;
        this.taxRate = taxRate;
        this.roomCapacity = roomCapacity;
    }

    // Calculates the additional charge for extra guests.
    public BigDecimal calculateExtraGuestCharge(int guestCount) {

        int extraGuests = Math.max(0, guestCount - roomCapacity);

        return extraGuestCharge.multiply(BigDecimal.valueOf(extraGuests));
    }

    // Calculates the tax on the taxable amount.
    public BigDecimal calculateTax(BigDecimal taxableAmount) {

        return taxableAmount.multiply(taxRate);
    }

    // Returns the daily room rate.
    public abstract BigDecimal getDailyRate();

    // Returns the room category.
    public KU2636159_RoomType getRoomType() {
        return roomType;
    }

    // Checks whether complimentary breakfast is included.
    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    // Returns the maximum room capacity.
    public int getRoomCapacity() {
        return roomCapacity;
    }
}
