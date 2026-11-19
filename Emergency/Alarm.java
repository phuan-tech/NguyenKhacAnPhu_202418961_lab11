// Nguyễn Khắc An Phú 202418961
package Emergency;

public class Alarm {
    private boolean isActive = false;

    // Kích hoạt
    public void activate() {
        if (isActive)
            return;
        isActive = true;
        System.out.println("[ALARM] Emergency alarm activated!");
    }

    // Ngừng kích hoạt
    public void deactivate() {
        if (!isActive)
            return;
        isActive = false;
        System.out.println("[ALARM] Emergency alarm deactivated.");
    }

    public boolean isActive() {
        return isActive;
    }
}
