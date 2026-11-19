// Nguyễn Khắc An Phú 202418961
package Observers;

import Models.AlertEvent;

public interface AlertObserver {
    // Cập nhật
    void update(AlertEvent alertEvent);
}
