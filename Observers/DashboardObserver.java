// Nguyễn Khắc An Phú 202418961
package Observers;

import Models.AlertEvent;

public class DashboardObserver implements AlertObserver {
    public void update(AlertEvent alertEvent) {
        if (alertEvent == null) {
            throw new IllegalArgumentException("Sự kiện cảnh báo không được null");
        }
        System.out.printf("[DASHBOARD] %s: %.1f%s [%s]%n",
                alertEvent.sensorName(), alertEvent.value(), alertEvent.unit(), alertEvent.severity());
    }
}
