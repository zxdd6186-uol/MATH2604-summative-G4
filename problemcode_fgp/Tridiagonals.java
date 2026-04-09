import static java.lang.Math.*;

import java.util.Arrays;

/**
 * Provides methods for working with tridiagonal matrices of the form of a 3 by n array,
 * with each row corresponding to upper, main and lower diagonals
 * 
 * Includes methods for giving tridiagonal matrices, validating tridiagonal matrices,
 * summing tridiagonal matrices, calculating the product of two tridiagonal matrices and
 * solving a linear system using tridiagonal matrices
 * 
 * Assumes finite double values and that matrices follow specificed storage format
 */
class Tridiagonals{

static double[][] exampleMatrix(int n) 
    {
        double m[][] = new double [1][1]; //dummy code: write your own
        return m; //dummy code: write your own
    }
/**
 * Checks whether a given matrix is a valid tridiagonal matrix of dimension 3 by n (with n >= 1)
 * 
 * @param a the matrix to be validated
 * @return true if the matrix is a valid tridiagonal matrix,
 * or false if the matrix is null, does not have exactly 3 rows, contains null rows,
 * or if the rows are not all of the same length or have length less than 1
 * 
 * Assumes all matrix entries are finite and real and that no further structural properties are required
 */
    public static boolean isValidTridiagonal(double[][] a) {
        if (a == null) {
            return false; 
        }
        if (a.length != 3) { // there are 3 diagonals in the matrix or 3 'rows' in the storage array
            return false;
        }
        if (a[0] == null || a[1] == null || a[2] == null) { //checking none of the diagonals are null
        return false;
        }
        int n = a[0].length; // n is the length of each of the storage rows 
        if (n<1) { // checks if the row length is too small (0)
            return false;
        }
        if (a[1].length != n){
            return false; //checking whether the main diagonal storage array is the same length as the superdiagonal
        }
        if (a[2].length != n){
            return false; //checking whether the subdiagonal storage array is the same length as the superdiagonal
        } 
        return true;//returns true if all the other tests pass
    }

/**
 * Sums two tridiagonal matrices of the dimension 3 by n (whith n >= 1)
 * 
 * @param a the first tridiagonal matrix
 * @param b the second tridiagonal matrix
 * @return a new tridiagonal matrix which is the sum of a and b,
 * or null if either matrix fails validation and/or the matrices have different dimension
 * 
 * Assumes all matrix entries are finite and real, that there is no restriction of numerical values and that arithmetic operations values will not overflow
 */

    public static double[][] sum(double[][] a, double[][] b) {
        if (!isValidTridiagonal(a) || !isValidTridiagonal(b)) { // Check if the two matrices are valid via the isValidTridiagonal function
            return null;
        }
        int n = a[0].length;
        if (b[0].length != n) { // Checks if a and b have the same number of columns
            return null;
        }
        double[][] result = new double [3][n]; // Creates a new 3 by n matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j ++) {
                result[i][j] = a[i][j] + b[i][j]; // The i,jth entry of the new matrix is the sum of the i,jth entries of a and b
            }
        }
        return result;

    }

    /**
 * Solves the linear system Tx = v where T is a tridiagonal matrix of dimension 3 by n (with n >= 1)
 * using the Thomas algorithm
 * 
 * @param T the tridiagonal matrix
 * @param v the right-hand side vector
 * @return a new vector x which is the solution to Tx = v,
 * or null if the matrix fails validation, the vector is null, and/or the dimensions do not match
 * 
 * Assumes all matrix and vector entries are finite and real, that there is no restriction on numerical values 
 * and that arithmetic operations will not overflow
 */

    public static double[] linearSolve(double[][] T, double[] v) {
        if (!isValidTridiagonal(T)){ //checks whether a is a valid tridiagonal matrix 
            return null;
        }
        if (v == null) { //checks wether the vector v exists 
            return null; 
        }
        int n = T[1].length; //checking the length of the main diagonal, and therefore size of matrix
        if (v.length != n) { //checking wether the vector is the right size 
            return null;
        }
        double[] upper = new double[T[0].length]; //copying the matrix diagonals and vector as the thomas algorithm modifies the values, and the input values must stay the same
        double[] main  = new double[T[1].length];
        double[] lower = new double[T[2].length];
        double[] b = new double[v.length];

        for (int i=0; i < upper.length; i++) {
            upper[i] = T[0][i]; //coppies each value from the superdiagonal of T into the copy array
        }
        for (int i = 0; i < main.length; i++) {
            main[i] = T[1][i]; //coppies each value from main diagonal of T into the copy array
        }
        for (int i = 0; i < lower.length; i++) {
            lower[i] = T[2][i]; //coppies each value from subdiagonal diagonal of T into the copy array
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = v[i]; //copying values from v to b  (the vector rhs)
        }
        double[] x = new double[n]; //creates new array to store the sollution
        
        if (n == 1) { //checks if the system only has one equation 
        x[0] = b[0] / main[0]; //solves the equation
        return x; 
        }
        for (int i = 1; i < n; i++) { //remove the lower diagonal on the matrix, start at i=1 because the first row has nothing to eliminate 
            double factor = lower[i - 1] / main[i - 1]; // calculates what is needed to multiply to eliminate the lower diagonal
            main[i] = main[i] - factor * upper[i - 1]; //updating the main diagonal after elminating the subdiagonal
            b[i] = b[i] - factor * b[i - 1]; //updating the rhs of the quational after the elimination step
        } 
        x[n - 1] = b[n - 1] / main[n - 1]; //back substitution 

        for (int i = n - 2; i >= 0; i--) { //going backwards through the equation, from the second last row to the first
            x[i] = (b[i] - upper[i] * x[i + 1]) / main[i]; //solving for xi
        }
        return x; //returning x from the equation Tx = v
    }
    public static void main(String[] args) {
    double[][] T = {
        {1, 1, 0},
        {2, 2, 2},
        {1, 1, 0}
    };

    double[] v = {4, 8, 8};

    System.out.println(isValidTridiagonal(T));

    double[] x = linearSolve(T, v);
    System.out.println(java.util.Arrays.toString(x));
    }
}