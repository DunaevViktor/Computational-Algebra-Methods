package lab2_koren;

public class Vector {
    double[] VectorR (double[][]a,int n,double[] x){
        double[] b = new double[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                b[i]+=(a[i][j]*x[j]);
            }
            b[i]-=a[i][n-1];
            }
        return b;
    }    
}

