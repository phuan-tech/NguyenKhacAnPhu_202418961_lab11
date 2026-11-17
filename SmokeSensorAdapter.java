public final class SmokeSensorAdapter implements Sensor {
    private final LegacySmokeDetecter legacy;
    
    public SmokeSensorAdapter(LegacySmokeDetecter legacy) {
        if (legacy == null) {
            throw new IllegalArgumentException("Thiết bị không được null");
        }

        this.legacy = legacy;
    }

    public double readValue() {
        return legacy.getSmokeLevel() / 10.0;
    }

    public String getUnit() {
        return "% smoke";
    }
}
