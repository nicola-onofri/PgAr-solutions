package utils;

import java.util.ArrayList;

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
    public static int gcd_2(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd_2(b, a % b);
    }

    /**
     * Implement Euclid's algorithms to calculate gcd
     * (greatest common divisor) between the numbers contained
     * in the given list
     *
     * @param arr the list of numbers
     * @return gcd between the numbers in the list
     */
    public static int gcd_arr(ArrayList<Integer> arr) {
        //recursion foot
        if (arr.size() == 2) return gcd_2(arr.get(0), arr.get(1));
        //if the list contains more than 2 elements apply the recursive call
        else return gcd_2(arr.get(0), gcd_arr(new ArrayList<>(arr.subList(1, arr.size() - 1))));
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
        //Casting to int is redundant!
        return (a * b) / MathUtils.gcd_2(a, b);
    }
}
