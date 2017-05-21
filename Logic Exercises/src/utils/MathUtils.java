package utils;

import java.util.ArrayList;

/**
 * Created by nicola on 20/05/17.
 */
public class MathUtils {
    /**
     * Calculates the sum of all the multiples of the given number,
     * below the bound.
     *
     * @param step  the step between each number
     * @param bound the upper bound for the multiples
     * @return the sum of the multiples
     */
    public static int sumMultiplesBelow_singleNumber(int step, int bound) {
        int nTerms = Math.floorDiv(bound, step);
        return (nTerms * (step + step * nTerms)) / 2;
    }

    /**
     * @param m1
     * @param m2
     * @param bound
     * @return
     */
    public static int sumMultiplesBelow_2numbers(int m1, int m2, int bound) {
        int s1 = sumMultiplesBelow_singleNumber(m1, bound);
        int s2 = sumMultiplesBelow_singleNumber(m2, bound);
        int s_lcm = sumMultiplesBelow_singleNumber(MathUtils.lcm(m1, m2), bound);

        return s1 + s2 - s_lcm;
    }

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
