import java.util.List;

public class AirConditionerRemote extends TeachingDevice {
    private List<String> compatibleRooms;
    
    public AirConditionerRemote(String id, String name, String storageLocation, List<String> compatibleRooms) {
        super(id, name, storageLocation);
        this.compatibleRooms = compatibleRooms;
    }

    public boolean isCompatibleWith(String roomId) {
        for (String compatibleRoom : this.compatibleRooms) {
            if (roomId == compatibleRoom) {
                return true;
            }
        }
        return false;
    }

    @Override 
    public boolean isReadyForUse() {
        return false;
    }

    @Override 
    public double getDefaultPriorityScore() {
        return 0;
    }
}
