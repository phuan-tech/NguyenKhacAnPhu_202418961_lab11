public class Alarm {
    private boolean isActive = false;

    public void activate() {
        if (isActive) return;
        isActive = true;
        System.out.println("[ALARM] Emergency alarm activated!");
    }

    public void deactivate() {
        if (!isActive) return;
        isActive = false;
        System.out.println("[ALARM] Emergency alarm deactivated.");
    }
}
