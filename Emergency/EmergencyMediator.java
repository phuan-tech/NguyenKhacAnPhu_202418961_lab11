// Nguyễn Khắc An Phú 202418961
public class EmergencyMediator implements IEmergencyMediator {
    private final Alarm alarm;
    private final DoorController doorController;
    private final VentilationController ventilationController;

    public EmergencyMediator(Alarm alarm, DoorController doorController, VentilationController ventilationController) {
        this.alarm = alarm;
        this.doorController = doorController;
        this.ventilationController = ventilationController;
    }

    public void handleEmergency(AlertEvent alertEvent) {
        if (alertEvent.severity().equals("WARNING")) {
            alarm.activate();
        } else if (alertEvent.severity().equals("CRITICAL")) {
            alarm.activate();
            doorController.openEmergencyExit();
            ventilationController.start();
        }
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public DoorController getDoorController() {
        return doorController;
    }

    public VentilationController getVentilationController() {
        return ventilationController;
    }
}
