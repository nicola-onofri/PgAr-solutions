package utils;

/**
 * Created by nicola on 20/05/17.
 */
public class MathUtils {
    /**
     * Implement Euclid's algorithms to calculate gcd
     * (greates common divisor) between two given numbers
     *
     * @param a first number
     * @param b second number
     * @return gcd between the given parameters
     */
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    /**
     * Calculates the lcm (least common multiple)
     * between two given numbers
     *
     * @param a first number
     * @param b second number
     * @return lcm between the given parameters
     */
    public static int lcm(int a, int b) {
        return (int) (a * b) / MathUtils.gcd(a, b);
    }
}
