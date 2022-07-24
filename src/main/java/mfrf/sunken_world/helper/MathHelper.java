package mfrf.sunken_world.helper;

public class MathHelper {

    /**
     *
     * @param current current value
     * @param min min value
     * @param max max value
     * @return the percentage;
     */
    public static float linearLerpWithMax(float current, float min, float max) {
        if (current <= min) return 0;
        if (current >= max) return 1;
        return (current - min) / (max - min);
    }
}
