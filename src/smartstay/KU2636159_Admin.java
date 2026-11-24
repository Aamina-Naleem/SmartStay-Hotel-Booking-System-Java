
package smartstay;

// Represents the administrator responsible for managing hotel rooms.
public class KU2636159_Admin {

    // Unique administrator ID.
    private String adminId;

    // Administrator login username.
    private String username;

    // Securely stored administrator password hash.
    private String passwordHash;

    // Creates an administrator account.
    public KU2636159_Admin(String adminId,
                           String username,
                           String passwordHash) {

        this.adminId = adminId;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // Adds a new room through the room manager.
    public boolean addRoom(
            KU2636159_RoomManager roomManager,
            KU2636159_Room room) {

        return roomManager.addRoom(room);
    }

    // Updates an existing room through the room manager.
    public boolean updateRoom(
            KU2636159_RoomManager roomManager,
            String roomId,
            KU2636159_Room updatedRoom) {

        return roomManager.updateRoom(roomId, updatedRoom);
    }

    // Removes a room through the room manager.
    public boolean removeRoom(
            KU2636159_RoomManager roomManager,
            String roomId) {

        return roomManager.removeRoom(roomId);
    }

    // Changes the availability status of a hotel room.
    public boolean manageRoomAvailability(
            KU2636159_RoomManager roomManager,
            String roomId,
            KU2636159_AvailabilityStatus status) {

        return roomManager.updateRoomAvailability(roomId, status);
    }

    // Displays all rooms registered in the hotel.
    public void viewAllRooms(
            KU2636159_RoomManager roomManager) {

        for (KU2636159_Room room : roomManager.getAllRooms()) {

            System.out.println(
                    "Room ID: " + room.getRoomId()
                    + "\nRoom Type: " + room.getRoomType()
                    + "\nBed Type: " + room.getBedType()
                    + "\nDaily Rate: LKR " + room.getDailyRate()
                    + "\nAvailability: " + room.getAvailabilityStatus()
                    + "\n-----------------------------");
        }
    }

    // Returns the administrator ID.
    public String getAdminId() {
        return adminId;
    }

    // Returns the administrator username.
    public String getUsername() {
        return username;
    }

    // Checks whether a supplied password hash matches this administrator's hash.
    public boolean verifyPasswordHash(String candidateHash) {
        return passwordHash != null && passwordHash.equals(candidateHash);
    }
}
