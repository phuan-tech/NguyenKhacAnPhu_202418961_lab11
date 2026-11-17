import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public final class SensorMonitor {
    private final List<AlertObserver> observers = new ArrayList<>();

    public void attach(AlertObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer không được null");
        }

        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(AlertObserver observer) {
        observers.remove(observer);
    }

    public void checkSensor(String sensorName, Sensor sensor) {
        double value = sensor.readValue();
        
        String severity = switch (Double.valueOf(value)) {
            case Double v when v >= 70 -> "CRITICAL";
            case Double v when v >= 50 -> "WARNING";
            default -> null;
        };

        if (severity == null) {
            return;
        }

        var alertEvent = new AlertEvent(sensorName, value, sensor.getUnit(), severity, LocalDateTime.now());

        notify(alertEvent);
    }

    private void notify(AlertEvent alertEvent) {
        List<AlertObserver> copyObservers = new ArrayList<>(observers);
        for (AlertObserver observer : copyObservers) {
            observer.update(alertEvent);
        }
    }
}
