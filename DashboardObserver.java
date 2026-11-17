public class DashboardObserver implements AlertObserver {
    public void update(AlertEvent alertEvent) {
        System.out.println("[DASHBOARD] " + alertEvent.sensorName() + 
                            " " + alertEvent.value() + " " + alertEvent.unit() + 
                            " " + alertEvent.severity());
    }
}
