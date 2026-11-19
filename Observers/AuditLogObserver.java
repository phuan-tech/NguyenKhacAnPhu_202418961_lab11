// Nguyễn Khắc An Phú 202418961
package Observers;

import Models.AlertEvent;
import java.util.List;
import java.util.ArrayList;

public class AuditLogObserver implements AlertObserver {
    private List<String> alertHistory = new ArrayList<>();

    public void update(AlertEvent alertEvent) {
        if (alertEvent == null) {
            throw new IllegalArgumentException("Sự kiện cảnh báo không được null");
        }

        alertHistory.add("[AUDIT LOG] " + alertEvent.occurredAt() + " | " +
                        alertEvent.sensorName() + " | " + alertEvent.value() + alertEvent.unit() +
                        " | " + alertEvent.severity());
    }

    // Lấy lịch sử cảnh báo
    public List<String> getAlertHistory() {
        return new ArrayList<>(alertHistory);
    }
}
