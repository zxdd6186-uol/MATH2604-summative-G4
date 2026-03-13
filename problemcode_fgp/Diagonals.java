import static org.junit.Assert.*;
/**
 * Provides four methods for performing some basic operations on diagonal matrices
 * These matrices are represented as arrays containing the diagonal matrices
 */
class Diagonals {

    public static double[] exampleMatrix() {
        double[] a = {10, 8, 5, -10, 7}; // creates the array for the diagonal elements of the matrix
        return a; //returns the array which represents the diagonal for the matrix
    }

/**
 * Computes the inverse of a diagonal matrix by calculating the reciprocal of each diagonal entry.
 * 
 * @param a an array which contains the diagonal entries of the matrix
 * @return results an array which contains the reciprocals of each of the diagonal entries from the input matrix,
 * or null in the exceptional case wherein the input array is null
 * 
 * Assumes that the matrix is invertible, none of the diagonal entries are zero
 */
    public static double[] inverse(double[] a) {
        if (a == null) {
            return null;
        }
        double[] result = new double[a.length]; // Creates a new array called result which is the same length as a
        for (int i = 0; i < a.length; i++) {
            result[i] = 1.0 / a[i]; // Calculates reciprocal of each entry
        }
        return result;
    }

}