import static java.lang.Math.*;

class ODE
{
    public static double solve(double a, int n)
    {
        double h = 1.0 / (n + 1);
        double[][] m = new double[3][n];
        double[] rhs = new double[n];

        for (int i = 0; i < n; i++)
        {
            double x = (i + 1) * h;
            m[1][i] = 2.0 + h * h * cos(x);
            rhs[i] = -h * h * a * x * x;
        }
        for (int i = 0; i < n - 1; i++)
        {
            m[0][i] = -1.0;
            m[2][i] = -1.0;
        }
        double[] w = Tridiagonals.linearSolve(m, rhs);
        double pos = 0.5 / h;
        int nearest = (int) pos;

        if (abs(pos - nearest) < 1e-10)
        {
            return w[nearest - 1];
        }
        else
        {
            int left = (int) floor(pos);
            int right = (int) ceil(pos);
            return (w[left - 1] + w[right - 1]) / 2.0;
        }
    }
}
