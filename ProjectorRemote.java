public class ProjectorRemote extends TeachingDevice {
    private int batteryLevel;

    public ProjectorRemote(String id, String name, String storageLocation, int batteryLevel) {
        super(id, name, storageLocation);
        
        // Kiểm tra mức pin
        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("Mức pin phải nằm trong khoảng 0-100");
        }
        this.batteryLevel = batteryLevel;
    }

    @Override 
    public boolean isReadyForUse() {
        if (this.getStatus() == DeviceStatus.AVAILABLE && this.batteryLevel >= 20) {
            return true;
        } else {
            return false;
        }
    }

    @Override 
    public double getDefaultPriorityScore() {
        double defaultPriorityScore = this.batteryLevel;
        return defaultPriorityScore;
    }

    public int getBatteryLevel() {
        return this.batteryLevel;
    }
}
