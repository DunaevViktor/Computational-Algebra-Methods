package lab2_koren;

public class Umnozit {
    double[][] doIt(double[][]mA, double[][]mB){
        int m = mA.length;
        int n = mB[0].length;
        int o = mB.length;
        double[][] res = new double[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < o; k++) {
                    res[i][j] += mA[i][k] * mB[k][j]; 
                }
            }
        }
        return res;
    }
    double[] doItWithVect(double[][]mA, double[]mB){
        int m = mA.length;
        double[] res = new double[m];      
        for (int i = 0; i < m; i++) {
            //System.out.println("res"+i + " = ");
                for (int k = 0; k < m; k++) {
                    //System.out.println(mA[i][k] + " * " + mB[k] + " + ");
                    res[i] += mA[i][k] * mB[k]; 
                }
        }
        return res;
    }
}
