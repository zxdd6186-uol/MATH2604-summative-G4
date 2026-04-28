/**
 * Provides four methods for performing some basic operations on diagonal matrices.
 * These matrices are represented as arrays containing the diagonal entries.
 */

import java.util.Arrays;

/**
 * Creates and returns an example diagonal matrix stored as a 1D array.
 *
 * @return an array representing the diagonal elements of a matrix
 */
class Diagonals {

    public static double[] exampleMatrix() { // creates the array for the diagonal elements of the matrix
        double[] a = {10, 8, 5, -10, 7}; //returns the array which represents the diagonal for the matrix
        return a;
    }

    /**
     * Computes the inverse of a diagonal matrix by calculating the reciprocal of each diagonal entry.
     *
     * @param a an array containing the diagonal entries of the matrix
     * @return an array containing the reciprocals of each diagonal entry,
     *         or null if the input array is null
     *
     * Assumes that the matrix is invertible and no diagonal entry is zero.
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

    /**
     * Computes the sum of two diagonal matrices.
     *
     * @param a the first diagonal matrix
     * @param b the second diagonal matrix
     * @return the sum of the two matrices, or null if either input is null
     *         or if the arrays have different lengths
     */
    public static double[] sum(double[] a, double[] b) {
        if (a == null || b == null) {
            return null;
        }

        if (a.length != b.length) {
            return null;
        }

        double[] result = new double[a.length];

        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }

        return result;
    }

    /**
     * Computes the product of two diagonal matrices.
     *
     * @param a the first diagonal matrix
     * @param b the second diagonal matrix
     * @return the product of the two matrices, or null if either input is null
     *         or if the arrays have different lengths
     */
    public static double[] product(double[] a, double[] b) {
        if (a == null || b == null) {
            return null;
        }

        if (a.length != b.length) {
            return null;
        }

        double[] result = new double[a.length];

        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] * b[i];
        }

        return result;
    }
}