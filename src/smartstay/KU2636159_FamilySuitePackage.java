
package smartstay;

import java.math.BigDecimal;

// Represents the Family Suite room package.
public class KU2636159_FamilySuitePackage extends KU2636159_RoomPackage {

    // Initialises the Family Suite package using the coursework pricing.
    public KU2636159_FamilySuitePackage() {

        super(
            KU2636159_RoomType.FAMILY_SUITE,
            new BigDecimal("18000.00"),
            true,
            new BigDecimal("2500.00"),
            new BigDecimal("0.15"),
            4
        );
    }

    // Returns the daily rate of the Family Suite package.
    @Override
    public BigDecimal getDailyRate() {
        return dailyRate;
    }
}
