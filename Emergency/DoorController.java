public class DoorController {
    private boolean isOpen = false;

    public void openEmergencyExit() {
        if (isOpen) return;

        isOpen = true;
        System.out.println("[DOOR] Emergency exit opened successfully.");
    }

    public void closeDoor() {
        if (!isOpen) return;

        isOpen = false;
        System.out.println("[DOOR] Emergency exit closed.");
    }
}
