
package smartstay;

import java.math.BigDecimal;

// Represents the Standard room package.
public class KU2636159_StandardPackage extends KU2636159_RoomPackage {

    // Initialises the Standard package using the coursework pricing.
    public KU2636159_StandardPackage() {

        super(
            KU2636159_RoomType.STANDARD,
            new BigDecimal("6000.00"),
            false,
            new BigDecimal("1000.00"),
            new BigDecimal("0.10"),
            2
        );
    }

    // Returns the daily rate of the Standard package.
    @Override
    public BigDecimal getDailyRate() {
        return dailyRate;
    }
}
