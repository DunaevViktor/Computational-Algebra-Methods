
package lab1vma;


public class OneSplittingScheme {
     //Схема единственного деления
    public double[][] split(double[][]M,  int str,int n){
        double splitter = M[str][str];
            for(int j=str;j<n+1;j++)
            {
                M[str][j]=M[str][j]/splitter;
            }
        for(int i=str+1;i<n;i++)
        {
            double temp = M[i][str];
            for(int j=str;j<n+1;j++)
            {
                M[i][j]=M[i][j]-(M[str][j]*temp);
            }
        }
        return M;
    }
    //Функция поиска вектора решения
    public double[] findVectorX (double[][] M,int n){  
        double[] X = new double[n];
        for(int i=n-1;i>=0;i--)
        {
            X[i]=M[i][n];
            for(int j=n-1;j>i;j--)
            {            
                X[i]=X[i]-M[i][j]*X[j];               
            }
            
        }      
        return X;
    }
    //Функция поиска вектора невязки
    public double[] check (double[][] M, int n,double[] X,int []A)
    {
        double [][] res = new double[n][n];double []vektorNev = new double [n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                res[i][A[j]]=X[j]*M[i][A[j]];
            }
        }
        for(int i=0;i<n;i++)
        {
            vektorNev[i]=M[i][n];
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                vektorNev[i]=vektorNev[i]-res[i][j];
            }
        }
        return vektorNev;
    }
}
