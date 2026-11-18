// Nguyễn Khắc An Phú 202418961
public class DoorController {
    private boolean isOpen = false;

    // Mở lối thoát hiểm
    public void openEmergencyExit() {
        if (isOpen) return;

        isOpen = true;
        System.out.println("[DOOR] Emergency exit opened successfully.");
    }

    // Đóng cửa 
    public void closeDoor() {
        if (!isOpen) return;

        isOpen = false;
        System.out.println("[DOOR] Emergency exit closed.");
    }
}
