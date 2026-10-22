import java.util.List;

public class Main {
    public static void main(String[] args) {
        TeachingDevice projectorRemote1 = new ProjectorRemote("MC001", 
                                                            "điều khiển máy chiếu 1", 
                                                            "Kho", 
                                                            90);
        TeachingDevice projectorRemote2 = new ProjectorRemote("MC002", 
                                                            "điều khiển máy chiếu 2", 
                                                            "Kho", 
                                                            50);
        TeachingDevice projectorRemote3 = new ProjectorRemote("MC003", 
                                                            "điều khiển máy chiếu 3", 
                                                            "Kho", 
                                                            10);
                                                            
        TeachingDevice airConditionerRemote1 = new AirConditionerRemote("DH001", 
                                                                        "Điều khiển điều hòa 1", 
                                                                        "Kho", 
                                                                        List.of("A001", "A003", "A004"));
        TeachingDevice airConditionerRemote2 = new AirConditionerRemote("DH002", 
                                                                        "Điều khiển điều hòa 2", 
                                                                        "Kho", 
                                                                        List.of("A002", "A005", "A006"));

        TeachingDevice wirelessMicrophone1 = new WirelessMicrophone("MP001", 
                                                                    "micro 1", 
                                                                    "Kho", 
                                                                    80, 
                                                                    85);
        TeachingDevice wirelessMicrophone2 = new WirelessMicrophone("MP002", 
                                                                    "micro 2", 
                                                                    "Kho", 
                                                                    25, 
                                                                    80);
        TeachingDevice wirelessMicrophone3 = new WirelessMicrophone("MP003", 
                                                                    "micro 3", 
                                                                    "Kho", 
                                                                    90, 
                                                                    30);
    }
}