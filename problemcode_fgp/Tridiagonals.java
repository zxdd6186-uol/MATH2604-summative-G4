import static java.lang.Math.*;

class Tridiagonals{

static double[][] exampleMatrix(int n) 
    {
        double m[][] = new double [1][1]; //dummy code: write your own
        return m; //dummy code: write your own
    }

    public static boolean isValidTridiagonal(double[][] a) {
        if (a == null) {
            return false; 
        }
        if (a.length != 3) { // there are 3 diagonals in the matrix or 3 'rows' in the storage array
            return false;
        }
        if (a[0] == null) {
            return false;
        }
        if (a[1] == null) {
            return false;
        }
        if (a[2] == null) { // checking none of the diagonals are null
            return false;
        }
        int n = a[0].length; // n is the length of each of the storage rows 
        if (n<1) { // checks if the row length is too small (0)
            return false;
        }
        if (a[1].length != n){
            return false; //checking weather the main diagonal storage array is the same length as the superdiagonal
        }
        if (a[2].length != n){
            return false; //checking whether the subdiagonal storage array is the same length as the superdiagonal
        } 
        return true;//returns true if all the other tests pass
    }
}