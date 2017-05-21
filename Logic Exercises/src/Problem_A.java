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
     * below the bound - inefficiently!
     *
     * @return the sum of the multiples of m1 and m2 below the bound
     */
    public int dumbResolution() {
        int sum = 0;

        for (int i = 1; i < this.bound; i++)
            if (i % this.m1 == 0 || i % this.m2 == 0) sum += i;
        return sum;
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
