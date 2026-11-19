// Nguyễn Khắc An Phú 202418961
package Observers;

import Models.AlertEvent;
import Emergency.IEmergencyMediator;

public class EmergencyBridgeObserver implements AlertObserver {
    private final IEmergencyMediator mediator;

    public EmergencyBridgeObserver(IEmergencyMediator mediator) {
        if (mediator == null) {
            throw new IllegalArgumentException("Mediator không được null");
        }
        this.mediator = mediator;
    }

    public void update(AlertEvent alertEvent) {
        if (alertEvent == null) {
            throw new IllegalArgumentException("Sự kiện cảnh báo không được null");
        }

        System.out.println("[EMERGENCY BRIDGE]");
        System.out.println(alertEvent.severity() + " detected.");
        System.out.println("Forwarding event to EmergencyMediator...");

        mediator.handleEmergency(alertEvent);
    }
}
