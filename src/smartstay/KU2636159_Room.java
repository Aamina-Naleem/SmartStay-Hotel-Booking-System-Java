
package smartstay;

// Represents an individual hotel room in SmartStay.
public class KU2636159_Room {

    // Unique identifier assigned to each room.
    private String roomId;

    // Type of bed available in the room.
    private KU2636159_BedType bedType;

    // Current availability condition of the room.
    private KU2636159_AvailabilityStatus availabilityStatus;

    // Package containing room type and pricing details.
    private KU2636159_RoomPackage roomPackage;

    // Creates a room with its basic details.
    public KU2636159_Room(String roomId,
                          KU2636159_BedType bedType,
                          KU2636159_AvailabilityStatus availabilityStatus,
                          KU2636159_RoomPackage roomPackage) {

        this.roomId = roomId;
        this.bedType = bedType;
        this.availabilityStatus = availabilityStatus;
        this.roomPackage = roomPackage;
    }

    // Returns the unique room ID.
    public String getRoomId() {
        return roomId;
    }

    // Returns the room category.
    public KU2636159_RoomType getRoomType() {
        return roomPackage.getRoomType();
    }

    // Returns the bed type.
    public KU2636159_BedType getBedType() {
        return bedType;
    }

    // Returns the daily room rate.
    public java.math.BigDecimal getDailyRate() {
        return roomPackage.getDailyRate();
    }

    // Returns the current availability status.
    public KU2636159_AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    // Updates the availability status of the room.
    public void setAvailabilityStatus(
            KU2636159_AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    // Assigns a room package to the room.
    public void setRoomPackage(KU2636159_RoomPackage roomPackage) {
        this.roomPackage = roomPackage;
    }

    // Checks whether the room is available for booking.
    public boolean isAvailable() {
        return availabilityStatus == KU2636159_AvailabilityStatus.AVAILABLE;
    }

        // Returns the room package assigned to this room.
    public KU2636159_RoomPackage getRoomPackage() {
        return roomPackage;
    }
}



