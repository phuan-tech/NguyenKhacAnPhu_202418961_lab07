public class DeviceNotReadyException extends Exception{
    private String deviceId;
    private DeviceStatus currentStatus;

    public DeviceNotReadyException(String deviceId, DeviceStatus currentStatus) {
        super("Thiết bị " + deviceId + " không sẵn sàng");
        this.deviceId = deviceId;
        this.currentStatus = currentStatus;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public DeviceStatus getCurrentStatus() {
        return this.currentStatus;
    }
}
