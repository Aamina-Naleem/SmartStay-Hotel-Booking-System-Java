
package smartstay;

import java.util.ArrayList;
import java.util.List;

// Manages guest records in the SmartStay system.
public class KU2636159_GuestManager {

    // Stores all registered guests.
    private List<KU2636159_Guest> guests;

    // Creates an empty guest collection.
    public KU2636159_GuestManager() {
        guests = new ArrayList<>();
    }

    // Registers a new guest in the system.
    public boolean registerGuest(KU2636159_Guest guest) {

        if (guest == null || findGuestById(guest.getGuestId()) != null) {
            return false;
        }

        guests.add(guest);
        guest.register();

        return true;
    }

    // Finds a registered guest using their unique ID.
    public KU2636159_Guest findGuestById(String guestId) {

        for (KU2636159_Guest guest : guests) {

            if (guest.getGuestId().equalsIgnoreCase(guestId)) {
                return guest;
            }
        }

        return null;
    }

    // Searches for guests using their full name.
    public List<KU2636159_Guest> searchGuestsByName(String name) {

        List<KU2636159_Guest> matchingGuests = new ArrayList<>();

        for (KU2636159_Guest guest : guests) {

            if (guest.getFullName().toLowerCase()
                    .contains(name.toLowerCase())) {

                matchingGuests.add(guest);
            }
        }

        return matchingGuests;
    }

    // Updates a guest's contact number and email address.
    public boolean updateGuest(String guestId,
                               String contactNumber,
                               String emailAddress) {

        KU2636159_Guest guest = findGuestById(guestId);

        if (guest != null) {
            guest.updateContactDetails(contactNumber, emailAddress);
            return true;
        }

        return false;
    }

    // Returns all registered guests.
    public List<KU2636159_Guest> getAllGuests() {
        return new ArrayList<>(guests);
    }
}
