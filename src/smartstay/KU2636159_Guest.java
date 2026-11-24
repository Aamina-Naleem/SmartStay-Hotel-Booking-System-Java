
package smartstay;

// Represents a registered guest in the SmartStay system.
public class KU2636159_Guest {

    // Unique identifier assigned to each guest.
    private String guestId;

    // NIC or passport number of the guest.
    private String nicOrPassport;

    // Full name of the guest.
    private String fullName;

    // Contact number of the guest.
    private String contactNumber;

    // Email address of the guest.
    private String emailAddress;

    // Creates a guest using the registration details.
    public KU2636159_Guest(String guestId, String nicOrPassport,
                           String fullName, String contactNumber,
                           String emailAddress) {

        this.guestId = guestId;
        this.nicOrPassport = nicOrPassport;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
    }

    // Registers the guest in the system.
    public void register() {
        System.out.println("Guest registered successfully: " + fullName);
    }

    // Updates the guest's contact information.
    public void updateContactDetails(String contactNumber,
                                     String emailAddress) {

        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
    }

    // Returns the guest's registration details.
    public String getGuestDetails() {

        return "Guest ID: " + guestId
                + "\nNIC/Passport: " + nicOrPassport
                + "\nFull Name: " + fullName
                + "\nContact Number: " + contactNumber
                + "\nEmail Address: " + emailAddress;
    }

    // Returns the unique guest ID.
    public String getGuestId() {
        return guestId;
    }

    // Returns the guest's full name.
    public String getFullName() {
        return fullName;
    }

    // Returns the guest's NIC or passport number.
    public String getNicOrPassport() {
        return nicOrPassport;
    }

    // Returns the guest's contact number.
    public String getContactNumber() {
        return contactNumber;
    }

    // Returns the guest's email address.
    public String getEmailAddress() {
        return emailAddress;
    }
}
