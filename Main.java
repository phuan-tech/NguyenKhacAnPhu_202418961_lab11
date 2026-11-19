// Nguyễn Khắc An Phú 202418961
import Models.AlertEvent;
import Sensors.Sensor;
import Sensors.LegacySmokeDetecter;
import Sensors.SmokeSensorAdapter;
import Observers.DashboardObserver;
import Observers.EmailAlertObserver;
import Observers.AuditLogObserver;
import Observers.EmergencyBridgeObserver;
import Monitoring.SensorMonitor;
import Emergency.Alarm;
import Emergency.DoorController;
import Emergency.VentilationController;
import Emergency.EmergencyMediator;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       HỆ THỐNG GIÁM SÁT AN TOÀN CAMPUS SAFETY HUB");
        System.out.println("==================================================\n");

        // ----------------------------------------------------
        // 1. KIỂM THỬ ADAPTER PATTERN
        // ----------------------------------------------------
        System.out.println(">>> 1. KIỂM THỬ ADAPTER PATTERN <<<");
        LegacySmokeDetecter legacyDetector = new LegacySmokeDetecter(735);
        Sensor smokeSensor = new SmokeSensorAdapter(legacyDetector);

        System.out.println("Cảm biến gốc (Legacy): " + legacyDetector.getDescription() + " | Raw value: " + legacyDetector.getSmokeLevel());
        System.out.println("Qua Adapter (ISensor): " + smokeSensor.readValue() + " " + smokeSensor.getUnit());
        System.out.println("--------------------------------------------------\n");

        // ----------------------------------------------------
        // 2. KHỞI TẠO CÁC THÀNH PHẦN MEDIATOR VÀ OBSERVER
        // ----------------------------------------------------
        System.out.println(">>> 2. THIẾT LẬP MEDIATOR VÀ CÁC THIẾT BỊ ỨNG PHÓ <<<");
        Alarm alarm = new Alarm();
        DoorController doorController = new DoorController();
        VentilationController ventilationController = new VentilationController();
        EmergencyMediator emergencyMediator = new EmergencyMediator(alarm, doorController, ventilationController);

        // Khởi tạo Monitor và các Observers
        SensorMonitor monitor = new SensorMonitor();
        DashboardObserver dashboard = new DashboardObserver();
        EmailAlertObserver emailAlert = new EmailAlertObserver();
        AuditLogObserver auditLog = new AuditLogObserver();
        EmergencyBridgeObserver emergencyBridge = new EmergencyBridgeObserver(emergencyMediator);

        // Đăng ký observers vào monitor (kiểm tra không bị trùng lặp)
        monitor.attach(dashboard);
        monitor.attach(dashboard); // Thử attach lại cùng 1 observer
        monitor.attach(emailAlert);
        monitor.attach(auditLog);
        monitor.attach(emergencyBridge);
        System.out.println("Đã đăng ký xong các Observer vào SensorMonitor.\n");

        // ----------------------------------------------------
        // 3. KỊCH BẢN 1: MỨC KHÓI BÌNH THƯỜNG (< 50%)
        // ----------------------------------------------------
        System.out.println(">>> 3. KỊCH BẢN 1: Khói ở mức bình thường (35%) <<<");
        legacyDetector.setSmokeLevel(350); // 35.0%
        System.out.println("Giá trị đọc: " + smokeSensor.readValue() + "% -> Kỳ vọng: Không có cảnh báo nào được phát.");
        monitor.checkSensor("SmokeSensor_RoomA", smokeSensor);
        System.out.println("Trạng thái Alarm: " + (alarm.isActive() ? "BẬT" : "TẮT"));
        System.out.println("--------------------------------------------------\n");

        // ----------------------------------------------------
        // 4. KỊCH BẢN 2: MỨC CẢNH BÁO WARNING (50% - < 70%)
        // ----------------------------------------------------
        System.out.println(">>> 4. KỊCH BẢN 2: Khói ở mức cảnh báo WARNING (60%) <<<");
        legacyDetector.setSmokeLevel(600); // 60.0%
        System.out.println("Giá trị đọc: " + smokeSensor.readValue() + "% -> Kỳ vọng: Phát WARNING, chỉ kích hoạt Alarm.");
        monitor.checkSensor("SmokeSensor_RoomA", smokeSensor);
        System.out.println("Trạng thái Alarm: " + (alarm.isActive() ? "BẬT" : "TẮT"));
        System.out.println("Trạng thái Door: " + (doorController.isOpen() ? "MỞ" : "ĐÓNG"));
        System.out.println("Trạng thái Ventilation: " + (ventilationController.isRunning() ? "CHẠY" : "DỪNG"));
        System.out.println("--------------------------------------------------\n");

        // Reset trạng thái thiết bị để test tiếp kịch bản Critical
        alarm.deactivate();

        // ----------------------------------------------------
        // 5. KỊCH BẢN 3: MỨC NGUY CẤP CRITICAL (>= 70%)
        // ----------------------------------------------------
        System.out.println(">>> 5. KỊCH BẢN 3: Khói ở mức nguy cấp CRITICAL (85%) <<<");
        legacyDetector.setSmokeLevel(850); // 85.0%
        System.out.println("Giá trị đọc: " + smokeSensor.readValue() + "% -> Kỳ vọng: Phát CRITICAL, kích hoạt Alarm + Mở Door + Chạy Ventilation.");
        monitor.checkSensor("SmokeSensor_RoomA", smokeSensor);
        System.out.println("Trạng thái Alarm: " + (alarm.isActive() ? "BẬT" : "TẮT"));
        System.out.println("Trạng thái Door: " + (doorController.isOpen() ? "MỞ" : "ĐÓNG"));
        System.out.println("Trạng thái Ventilation: " + (ventilationController.isRunning() ? "CHẠY" : "DỪNG"));
        System.out.println("--------------------------------------------------\n");

        // ----------------------------------------------------
        // 6. KIỂM THỬ DETACH OBSERVER
        // ----------------------------------------------------
        System.out.println(">>> 6. KIỂM THỬ HỦY ĐĂNG KÝ (DETACH) OBSERVER <<<");
        System.out.println("Hủy đăng ký EmailAlertObserver...");
        monitor.detach(emailAlert);

        legacyDetector.setSmokeLevel(900); // 90.0%
        System.out.println("Kiểm tra lại cảm biến với mức khói 90% (Email không được hiển thị thông báo):");
        monitor.checkSensor("SmokeSensor_RoomA", smokeSensor);
        System.out.println("--------------------------------------------------\n");

        // ----------------------------------------------------
        // 7. KIỂM TRA LỊCH SỬ AUDIT LOG
        // ----------------------------------------------------
        System.out.println(">>> 7. KIỂM TRA LỊCH SỬ NHẬT KÝ (AUDIT LOG) <<<");
        for (String log : auditLog.getAlertHistory()) {
            System.out.println(log);
        }
        System.out.println("\n==================================================");
        System.out.println("           HOÀN TẤT TẤT CẢ KIỂM THỬ");
        System.out.println("==================================================");
    }
}
