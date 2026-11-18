// Nguyễn Khắc An Phú 202418961
public final class LegacySmokeDetecter {
    private int smokeLevel;
    public LegacySmokeDetecter(int smokeLevel) {
        if (smokeLevel < 0 || smokeLevel > 1000) {
            throw new IllegalArgumentException("Mức độ khói không hợp lệ");
        }
        this.smokeLevel = smokeLevel;
    }

    public int getSmokeLevel() {
        return smokeLevel;
    }

    public String getDescription() {
        return "Legacy smoke detector";
    }

    public void setSmokeLevel(int value) {
        smokeLevel = Math.clamp(value, 0, 1000);
    }
}
