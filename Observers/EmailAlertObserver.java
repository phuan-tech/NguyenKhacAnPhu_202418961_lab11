// Nguyễn Khắc An Phú 202418961
package Observers;

import Models.AlertEvent;

public class EmailAlertObserver implements AlertObserver {
    public void update(AlertEvent alertEvent) {
        if (alertEvent == null) {
            throw new IllegalArgumentException("Sự kiện cảnh báo không được null");
        }
        System.out.println("[EMAIL ALERT]");
        System.out.println("Sensor: " + alertEvent.sensorName());
        System.out.println("Value: " + alertEvent.value() + alertEvent.unit());
        System.out.println("Severity: " + alertEvent.severity());
        System.out.println("Time: " + alertEvent.occurredAt());
    }
}
