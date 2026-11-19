// Nguyễn Khắc An Phú 202418961
package Emergency;

import Models.AlertEvent;

public interface IEmergencyMediator {
    // Xử lý cảnh báo
    void handleEmergency(AlertEvent alertEvent);
}
