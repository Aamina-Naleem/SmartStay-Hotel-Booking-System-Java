
package smartstay;

// Handles errors caused by violations of hotel booking rules.
public class KU2636159_BookingRuleException extends Exception {

    // Creates a booking rule exception with a descriptive message.
    public KU2636159_BookingRuleException(String message) {
        super(message);
    }
}
