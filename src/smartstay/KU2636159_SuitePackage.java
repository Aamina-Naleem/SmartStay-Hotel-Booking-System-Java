
package smartstay;

import java.math.BigDecimal;

// Represents the Suite room package.
public class KU2636159_SuitePackage extends KU2636159_RoomPackage {

    // Initialises the Suite package using the coursework pricing.
    public KU2636159_SuitePackage() {

        super(
            KU2636159_RoomType.SUITE,
            new BigDecimal("14000.00"),
            true,
            new BigDecimal("2000.00"),
            new BigDecimal("0.15"),
            2
        );
    }

    // Returns the daily rate of the Suite package.
    @Override
    public BigDecimal getDailyRate() {
        return dailyRate;
    }
}
