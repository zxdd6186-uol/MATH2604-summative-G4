import static org.junit.Assert.*;

class Diagonals {

    public static double[] exampleMatrix() {
        double[] a = {10, 8, 5, -10, 7}; // creates the array for the diagonal elements of the matrix
        return a; //returns the array which represents the diagonal for the matrix
    }

    public static double[] inverse(double[] a) {
        if (a == null) {
            return null;
        }
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = 1.0 / a[i];
        }
        return result;
    }

}