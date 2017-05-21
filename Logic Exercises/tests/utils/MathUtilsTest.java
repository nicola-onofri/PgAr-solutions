package utils;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by nicola on 21/05/17.
 */
public class MathUtilsTest {
    @Test
    public void gcd_2_coprimes() throws Exception {
        assertEquals(1, MathUtils.gcd_2(3, 5));
    }

    @Test
    public void gcd_2_multiple() throws Exception {
        assertEquals(3, MathUtils.gcd_2(3, 9));
    }

    @Test
    public void gcd_arr_ArrayLength2_coprimes() throws Exception {
        ArrayList<Integer> params = new ArrayList<>();
        params.add(2);
        params.add(3);
        assertEquals(1, MathUtils.gcd_arr(params));
    }

    @Test
    public void gcd_arr_ArrayLength2_multiples() throws Exception {
        ArrayList<Integer> params = new ArrayList<>();
        params.add(2);
        params.add(4);
        assertEquals(2, MathUtils.gcd_arr(params));
    }

    @Test
    public void gcd_arr_ArrayLengthMoreThan2_coprimes() throws Exception {
        ArrayList<Integer> params = new ArrayList<>();
        params.add(2);
        params.add(3);
        params.add(5);
        params.add(9);
        assertEquals(1, MathUtils.gcd_arr(params));
    }

    @Test
    public void gcd_arr_ArrayLengthMoreThan2_multiples() throws Exception {
        ArrayList<Integer> params = new ArrayList<>();
        params.add(2);
        params.add(12);
        params.add(4);
        params.add(6);
        assertEquals(2, MathUtils.gcd_arr(params));
    }

    @Test
    public void lcm_coprimes() throws Exception {
        assertEquals(6, MathUtils.lcm(2, 3));
    }

    @Test
    public void lcm_multiples() throws Exception {
        assertEquals(4, MathUtils.lcm(2, 4));
    }


    @Test
    public void sumMultiplesBelow_singleNumber() throws Exception {
        assertEquals(163845, MathUtils.sumMultiplesBelow_singleNumber(3, 1000));
    }
}