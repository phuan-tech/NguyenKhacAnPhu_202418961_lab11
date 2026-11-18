// Nguyễn Khắc An Phú 202418961
public class VentilationController {
    private boolean isRunning = false;

    // Bắt đầu
    public void start() {
        if (isRunning) {
            System.out.println("[VENTILATION] Ventilation system is already running.");
            return;
        }

        isRunning = true;
        System.out.println("[VENTILATION] Ventilation system started.");
    }

    // Dừng lại
    public void stop() {
        if (!isRunning) {
            System.out.println("[VENTILATION] Ventilation system is already stopped.");
            return;
        }

        isRunning = true;
        System.out.println("[VENTILATION] Ventilation system stopped.");
    }
}
