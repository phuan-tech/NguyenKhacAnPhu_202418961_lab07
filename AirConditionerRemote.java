import java.util.List;
import java.util.Collections;

public class AirConditionerRemote extends TeachingDevice {
    private List<String> compatibleRooms;
    
    public AirConditionerRemote(String id, String name, String storageLocation, List<String> compatibleRooms) {
        super(id, name, storageLocation);
        this.compatibleRooms = compatibleRooms;
    }

    public boolean isCompatibleWith(String roomId) {
        // Kiểm tra mã phòng
        if (roomId == null || roomId.isBlank()) {
            throw new IllegalArgumentException();
        }

        for (String compatibleRoom : this.compatibleRooms) {
            if (roomId == compatibleRoom) {
                return true;
            }
        }
        return false;
    }

    @Override 
    public boolean isReadyForUse() {
        return this.getStatus() == DeviceStatus.AVAILABLE;
    }

    @Override 
    public double getDefaultPriorityScore() {
        return 100;
    }

    public List<String> getCompatibleRooms() {
        return Collections.unmodifiableList(this.compatibleRooms);
    }
}
