
package smartstay;

import java.math.BigDecimal;

// Represents the Deluxe room package.
public class KU2636159_DeluxePackage extends KU2636159_RoomPackage {

    // Initialises the Deluxe package using the coursework pricing.
    public KU2636159_DeluxePackage() {

        super(
            KU2636159_RoomType.DELUXE,
            new BigDecimal("9000.00"),
            true,
            new BigDecimal("1500.00"),
            new BigDecimal("0.12"),
            2
        );
    }

    // Returns the daily rate of the Deluxe package.
    @Override
    public BigDecimal getDailyRate() {
        return dailyRate;
    }
}
