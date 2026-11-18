// Nguyễn Khắc An Phú 202418961
public class DashboardObserver implements AlertObserver {
    public void update(AlertEvent alertEvent) {
        if (alertEvent == null) {
            throw new IllegalArgumentException("Sự kiện cảnh báo không được null");
        }
        System.out.println("[DASHBOARD] " + alertEvent.sensorName() + 
                            " " + alertEvent.value() + " " + alertEvent.unit() + 
                            " " + alertEvent.severity());
    }
}
