
package smartstay;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Manages hotel room records and room availability.
public class KU2636159_RoomManager {

    private List<KU2636159_Room> rooms;

    // Initialises the room collection.
    public KU2636159_RoomManager() {
        rooms = new ArrayList<>();
    }

    // Adds a room if it is valid and its ID is unique.
    public boolean addRoom(KU2636159_Room room) {

        if (room == null) {
            return false;
        }

        if (findRoomById(room.getRoomId()) != null) {
            return false;
        }

        rooms.add(room);
        return true;
    }

    // Updates an existing room using its room ID.
    public boolean updateRoom(
            String roomId,
            KU2636159_Room updatedRoom) {

        if (roomId == null || updatedRoom == null) {
            return false;
        }

        if (!roomId.equalsIgnoreCase(updatedRoom.getRoomId())) {
            return false;
        }

        for (int i = 0; i < rooms.size(); i++) {

            if (rooms.get(i).getRoomId().equalsIgnoreCase(roomId)) {
                rooms.set(i, updatedRoom);
                return true;
            }
        }

        return false;
    }

    // Removes a room using its room ID.
    public boolean removeRoom(String roomId) {

        KU2636159_Room room = findRoomById(roomId);

        if (room != null) {
            rooms.remove(room);
            return true;
        }

        return false;
    }

    // Finds a room using its room ID.
    public KU2636159_Room findRoomById(String roomId) {

        if (roomId == null) {
            return null;
        }

        for (KU2636159_Room room : rooms) {

            if (room.getRoomId().equalsIgnoreCase(roomId)) {
                return room;
            }
        }

        return null;
    }

    // Searches for available rooms of a selected type.
    public List<KU2636159_Room> searchAvailableRooms(
            KU2636159_RoomType roomType,
            LocalDate checkIn,
            LocalDate checkOut) {

        List<KU2636159_Room> availableRooms = new ArrayList<>();

        if (roomType == null
                || checkIn == null
                || checkOut == null
                || !checkOut.isAfter(checkIn)) {
            return availableRooms;
        }

        for (KU2636159_Room room : rooms) {

            if (room.getRoomType() == roomType && room.isAvailable()) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    // Updates the availability status of an existing room.
    public boolean updateRoomAvailability(
            String roomId,
            KU2636159_AvailabilityStatus status) {

        KU2636159_Room room = findRoomById(roomId);

        if (room == null || status == null) {
            return false;
        }

        room.setAvailabilityStatus(status);
        return true;
    }

    // Returns a copy of all registered rooms.
    public List<KU2636159_Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }
}
