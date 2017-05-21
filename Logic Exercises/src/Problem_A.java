/**
 * Created by nicola on 20/05/17.
 */
public class Problem_A {
    private int m1, m2, bound;

    //Class constructor
    public Problem_A(int m1, int m2, int bound) {
        this.m1 = m1;
        this.m2 = m2;
        this.bound = bound;
    }

    /**
     * Calculates the sum of all the multiples of the given number,
     * below the bound.
     *
     * @param step  the step between each number
     * @param bound the bound value
     * @return the sum of the multiples
     */
    public int sumMultiplesBelow_singleNumber(int step, int bound) {
        int nTerms = Math.floorDiv(bound, step);
        return (nTerms * (step + step * nTerms)) / 2;
    }

    public int sumMultiplesBelow_multipleNumbers(int m1, int m2, int bound){
        int sum = 0;
        int s1 = sumMultiplesBelow_singleNumber(m1, bound);
        int s2 = sumMultiplesBelow_singleNumber(m2, bound);
        return 2;
    }

    //Getters and setters
    public int getM1() {
        return m1;
    }

    public void setM1(int m1) {
        this.m1 = m1;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public int getBound() {
        return bound;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }
}
