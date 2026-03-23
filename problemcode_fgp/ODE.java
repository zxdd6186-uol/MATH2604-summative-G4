import static java.lang.Math.*;

/**
 * Provides a method to approximate the solution of a second-order
 * differential equation
 */
class ODE
{
/**
 * Approximates the value of f(0.5) for the second-order differential
 * equation with boundary conditions
 *
 * @param a the constant value in the differential equation
 * @param n the typically large and positive integer
 * @return an approximation of f(0.5)
 * 
 * Assumes n is a positive value (n > 0)
 */    
    public static double solve(double a, int n)
    {
        double h = 1.0 / (n + 1); // Divides the range [0,1] into n + 1 equal-sized intervals
        double[][] m = new double[3][n]; // Stores tridiagonal matrix in compact form
        double[] rhs = new double[n];

        for (int i = 0; i < n; i++) // Fills the main diagonal of the matrix and the RHS vector
        {
            double x = (i + 1) * h;
            m[1][i] = 2.0 + h * h * cos(x);
            rhs[i] = -h * h * a * x * x;
        }
        for (int i = 0; i < n - 1; i++) // Fills upper and lower diagonals with -1
        {
            m[0][i] = -1.0;
            m[2][i] = -1.0;
        }
        double[] w = Tridiagonals.linearSolve(m, rhs); // Solves Mw = RHS using the earlier implemented tridiagonals solver
        double pos = 0.5 / h; // Converts 0.5 into grid position
        int nearest = (int) pos;

        if (abs(pos - nearest) < 1e-10) // If at a grid point then returns the exact value
        {
            return w[nearest - 1];
        }
        else
        {
            int left = (int) floor(pos);
            int right = (int) ceil(pos);
            return (w[left - 1] + w[right - 1]) / 2.0; // Returns the mean of the two grid points before and after 0.5 to approximate f(0.5)
        }
    }
}
