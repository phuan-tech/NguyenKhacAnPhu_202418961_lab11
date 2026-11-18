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
        
    }
}
