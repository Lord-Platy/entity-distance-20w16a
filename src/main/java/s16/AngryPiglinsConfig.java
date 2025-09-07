package s16;

public class AngryPiglinsConfig {
    private static boolean angryPiglins = false;

    public static boolean isEnabled() {
        return angryPiglins;
    }

    public static void setEnabled(boolean value) {
        angryPiglins = value;
    }
}
