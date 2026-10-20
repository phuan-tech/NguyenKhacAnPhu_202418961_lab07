public abstract class TeachingDevice implements IIdentifiable {
    private String id;
    private String name;
    private String storageLocation;
    private DeviceStatus status = DeviceStatus.AVAILABLE;

    public TeachingDevice(String id, String name, String storageLocation) {
        //Kiểm tra mã thiết bị
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Mã thiết bị không hợp lệ");
        }

        // Kiểm tra tên thiết bị
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tên thiết bị không hợp lệ");
        }

        //Kiểm tra vị trí lưu trữ
        if (storageLocation == null || storageLocation.isBlank()) {
            throw new IllegalArgumentException("Vị trí lưu trữ thiết bị không hợp lệ");
        }

        this.id = id;
        this.name = name;
        this.storageLocation = storageLocation;
    }

    // Kiểm tra trạng thái có thể mượn hay không
    public abstract boolean isReadyForUse();

    // Lấy điểm ưu tiên
    public abstract double getDefaultPriorityScore();

    public void Borrow(){
        if (this.isReadyForUse()) {
            this.status = DeviceStatus.BORROWED;
        } else {
            return;
        }
    };

    public void Return(){
        if (this.status == DeviceStatus.BORROWED) {
            this.status = DeviceStatus.AVAILABLE;
        } else {
            return;
        }
    };

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getStorageLocation() {
        return this.storageLocation;
    }

    public DeviceStatus getStatus() {
        return this.status;
    }

    protected void setStatus(DeviceStatus status) {
        this.status = status;
    }
}
