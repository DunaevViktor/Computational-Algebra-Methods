package lab2_koren;

public class Matrix {
    double[] d;
    double[][] transpored(double[][]a){
        double[][] ta = new double[a.length][a.length];
        for(int i=0;i<a.length;i++)
            for(int j=0;j<a.length;j++)
                ta[i][j]=a[j][i];
        return ta;
    }
    double sign(double element) 
    { 
        if(element<0) 
        { 
            return -1; 
        }
        return 1; 
    } 
    double[][] findS(double[][]A){
        int n = A.length;
        d = new double[n];
        double[][] S=new double[n][n];
        d[0]=sign(A[0][0]);
        for (int i = 0; i < n; ++i)
	{
		double sum = A[i][i];
		for (int k = 0; k < i; ++k){
                    sum -= S[k][i] * S[k][i] * d[k];
                    //System.out.println("sum = "+sum);
                }
                d[i]=sign(A[i][i]-Math.abs(sum));
                //System.out.println("d"+(i+1) + " = "+d[i]);
		S[i][i] = Math.sqrt(sum/d[i]);
		for (int j = 0; j < n; ++j)
                	if (j > i)
			{
                            sum = A[i][j];
                            for (int k = 0; k < i; ++k)
				sum -= S[k][j] * S[k][i] * d[k];
                            S[i][j] = sum / (d[i]*S[i][i]);
			}
			else if (j < i)
                            S[i][j] = 0;
	}
        return S;
    }
    double[] findY (double[][] S, double[] b)
    {
        int n = S.length;
        double[] y =new double[n];
	for (int i = 0; i < n; ++i)
	{
		double sum = b[i];
		for (int k = 0; k < i; ++k)
			sum -= S[k][i] * y[k];
                //System.out.println("y"+(i+1) + " = " + sum + " / " + S[i][i]);
		y[i] = sum / S[i][i];
	}
        return y;
    }
    double[] findX (double[][] S, double[] y)
    {
        int n = S.length;
        double[] x = new double[n];
	for (int i = n-1 ; i >= 0; --i)
	{
		double sum = y[i];
		for (int k = i + 1; k < n; ++k)
			sum -= S[i][k] * x[k]*d[i];
		x[i] = sum / (S[i][i] * d[i]);
	}
        return x;
    }
    double findDet (double[][] S)
    {
        int n = S.length;
	double det = 1;
	for (int i = 0; i < n; ++i)
		det *= d[i] * S[i][i];
	return det;
    }
}
