public class WirelessMicrophone extends TeachingDevice {
    private int batteryLevel;
    private int signalQuality;

    public WirelessMicrophone(String id, String name, String storageLocation, int batteryLevel, int signalQuality) {
        super(id, name, storageLocation);

        //Kiểm tra mức pin
        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("Mức pin phải nằm trong khoảng 0-100");
        }

        //Kiểm tra chất lượng tín hiệu
        if (signalQuality < 0 || signalQuality > 100) {
            throw new IllegalArgumentException("Chất lượng tín hiệu phải nằm trong khoảng 0-100");
        }

        this.batteryLevel = batteryLevel;
        this.signalQuality = signalQuality;
    }

    @Override 
    public boolean isReadyForUse() {
        return this.getStatus() == DeviceStatus.AVAILABLE && this.batteryLevel >= 30 && this.signalQuality >= 40;
    }

    @Override 
    public double getDefaultPriorityScore() {
        double defaultPriorityScore = (this.batteryLevel + this.signalQuality) / 2;
        return defaultPriorityScore;
    }

    public int getBatteryLevel() {
        return this.batteryLevel;
    }

    public int getSignalQuality() {
        return this.signalQuality;
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Mức pin: " + this.batteryLevel);
        System.out.println("Chất lượng tín hiệu: " + this.signalQuality);
    }
}
