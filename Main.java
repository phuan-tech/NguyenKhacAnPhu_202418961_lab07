import java.util.List;

public class Main {
    public static void main(String[] args) throws DeviceNotReadyException, DuplicateIdException, NoSuitableItemException {
        ProjectorRemote projectorRemote1 = new ProjectorRemote("MC001", 
                                                            "điều khiển máy chiếu 1", 
                                                            "Kho", 
                                                            90);
        ProjectorRemote projectorRemote2 = new ProjectorRemote("MC002", 
                                                            "điều khiển máy chiếu 2", 
                                                            "Kho", 
                                                            50);
        ProjectorRemote projectorRemote3 = new ProjectorRemote("MC003", 
                                                            "điều khiển máy chiếu 3", 
                                                            "Kho", 
                                                            10);
                                                            
        AirConditionerRemote airConditionerRemote1 = new AirConditionerRemote("DH001", 
                                                                        "Điều khiển điều hòa 1", 
                                                                        "Kho", 
                                                                        List.of("A001", "A003", "A004"));
        AirConditionerRemote airConditionerRemote2 = new AirConditionerRemote("DH002", 
                                                                        "Điều khiển điều hòa 2", 
                                                                        "Kho", 
                                                                        List.of("A002", "A005", "A006"));

        WirelessMicrophone wirelessMicrophone1 = new WirelessMicrophone("MP001", 
                                                                    "micro 1", 
                                                                    "Kho", 
                                                                    80, 
                                                                    85);
        WirelessMicrophone wirelessMicrophone2 = new WirelessMicrophone("MP002", 
                                                                    "micro 2", 
                                                                    "Kho", 
                                                                    25, 
                                                                    80);
        WirelessMicrophone wirelessMicrophone3 = new WirelessMicrophone("MP003", 
                                                                    "micro 3", 
                                                                    "Kho", 
                                                                    90, 
                                                                    30);

        IRepository<TeachingDevice> repository1 = new InMemoryRepository<>();

        System.out.println("1. Thêm thiết bị vào repository");
        System.out.println("Thêm tất cả các thiết bị đã tạo vào repository1: ");
        repository1.add(projectorRemote1);
        repository1.add(projectorRemote2);
        repository1.add(projectorRemote3);
        repository1.add(airConditionerRemote1);
        repository1.add(airConditionerRemote2);
        repository1.add(wirelessMicrophone1);
        repository1.add(wirelessMicrophone2);
        repository1.add(wirelessMicrophone3);
        System.out.println("Thêm các thiết bị thành công");
        System.out.println("---------------------------------");

        System.out.println("2. Thử thêm hai thiết bị trùng mã");
        TeachingDevice projectorRemoteX = new ProjectorRemote("MC001", 
                                                            "điều khiển máy chiếu", 
                                                            "Kho", 
                                                            90);
        System.out.println("Thử thêm thiết bị có mã trùng với projectorRemote1: ");
        try {
            repository1.add(projectorRemoteX);
            System.out.println("Thêm thiết bị thành công");
        } catch (DuplicateIdException e) {
            System.out.println("LỖI! " + e.getMessage());
        }
        System.out.println("---------------------------------");

        System.out.println("3. Tìm thiết bị theo mã");
        System.out.println("Tìm thiết bị airConditionerRemote1 theo mã: ");
        repository1.findById("DH001").displayInfo();
        System.out.println("Tìm thiết bị wirelessMicrophone2 theo mã: ");
        repository1.findById("MP002").displayInfo();
        System.out.println("---------------------------------");

        System.out.println("4. Lọc các thiết bị sẵn sàng");
        System.out.println("Lọc các thiết bị sẵn sàng trong repository1: ");
        List<TeachingDevice> candidates = repository1.findAll(teachingDevice -> teachingDevice.isReadyForUse());
        for (TeachingDevice candidate : candidates) {
            candidate.displayInfo();
            System.out.println("---------------------------------");
        }

        System.out.println("5. Chọn điều khiển máy chiếu có điểm cao nhất");
        IRepository<ProjectorRemote> projectorRepository = new InMemoryRepository<>();
        projectorRepository.add(projectorRemote2);
        projectorRepository.add(projectorRemote1);
        projectorRepository.add(projectorRemote3);
        ISelectionStrategy<ProjectorRemote> projectorStrategy = new HighestPriorityStrategy<>(device -> device.getDefaultPriorityScore());
        AllocationService<ProjectorRemote> projectorService = new AllocationService<>(projectorRepository, projectorStrategy);
        ProjectorRemote projectorRemoteSelected = projectorService.allocate(device -> device.isReadyForUse());
        projectorRemoteSelected.displayInfo();
        System.out.println("---------------------------------");

        System.out.println("6. Chọn điều khiển điều hòa tương thích với một phòng");
        IRepository<AirConditionerRemote> airConditionerRepository = new InMemoryRepository<>();
        airConditionerRepository.add(airConditionerRemote1);
        airConditionerRepository.add(airConditionerRemote2);
        ISelectionStrategy<AirConditionerRemote> airConditionerStrategy = new FirstAvailableStrategy<>();
        AllocationService<AirConditionerRemote> airConditionerService = new AllocationService<>(airConditionerRepository, airConditionerStrategy);
        System.out.println("Chọn điều khiển điều hòa tương thích với phòng A001: ");
        AirConditionerRemote airCRSelected = airConditionerService.allocate(device -> device.isReadyForUse() && device.isCompatibleWith("A001"));
        airCRSelected.displayInfo();
        System.out.println("---------------------------------");

        System.out.println("7. Chọn micro đầu tiên sẵn sàng");
        IRepository<WirelessMicrophone> microRepository = new InMemoryRepository<>();
        microRepository.add(wirelessMicrophone1);
        microRepository.add(wirelessMicrophone2);
        microRepository.add(wirelessMicrophone3);
        ISelectionStrategy<WirelessMicrophone> microStrategy = new FirstAvailableStrategy<>();
        AllocationService<WirelessMicrophone> microService = new AllocationService<>(microRepository, microStrategy);
        WirelessMicrophone microSelected = microService.allocate(device -> device.isReadyForUse());
        microSelected.displayInfo();
        System.out.println("---------------------------------");

        System.out.println("8. Đổi chiến lược trong thời gian chạy và quan sát kết quả");
        System.out.println("(Dùng lại AllocationService ở câu 5)");
        projectorService.changeStrategy(new FirstAvailableStrategy<>());
        ProjectorRemote selected = projectorService.allocate(device -> device.isReadyForUse());
        selected.displayInfo();
        System.out.println("---------------------------------");

        System.out.println("9. Thử cấp phát khi không có ứng viên phù hợp");
        try {
            WirelessMicrophone test = microService.allocate(device -> false);
            test.displayInfo();
        } catch (NoSuitableItemException e) {
            System.out.println("Lỗi! " + e.getMessage());
        }
    }
}