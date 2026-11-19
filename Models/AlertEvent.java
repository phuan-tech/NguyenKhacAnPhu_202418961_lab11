// Nguyễn Khắc An Phú 202418961
package Models;

import java.time.LocalDateTime;

public record AlertEvent(String sensorName, double value, String unit, String severity, LocalDateTime occurredAt) {}
