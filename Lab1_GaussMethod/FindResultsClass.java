package Lab1VMAGaussMethod;

public class FindResultsClass {
    //Функция поиска обратной матрицы
    public double[][] inverseMatricsLook(double [][]M, int n){
        double [][]workMatr = new double[n][n];
        double [][]resultMatr = new double[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                workMatr[i][j]=M[i][j];
                resultMatr[i][j]=0;
                if(i==j)
                {
                    resultMatr[i][j]=1;
                }
            }
        }          
        for(int i=0;i<n;i++)
        {
            double delitel = workMatr[i][i];
            for(int j=0;j<n;j++)
            {             
                workMatr[i][j]=workMatr[i][j]/delitel;
                resultMatr[i][j]/=delitel;
            }
            for(int g=i+1;g<n;g++)
            {
                double mnogitel = workMatr[g][i];            
                for(int f=0;f<n;f++)
                {                
                    workMatr[g][f]=workMatr[g][f]-workMatr[i][f]*mnogitel;
                    resultMatr[g][f]=resultMatr[g][f]-resultMatr[i][f]*mnogitel;
                }
            }             
        }           
        VectorClass[] vc = new VectorClass[n];
        VectorClass f = new VectorClass(resultMatr[0],n);
        for(int str = n-1;str>=0;str--)
        {
            vc[str]=f.findVectorMethod(resultMatr, workMatr, str, n, vc);
        }       
        resultMatr=f.createMatrixFromVectors(vc, n);        
        return resultMatr;
    }
    //Функция проверки обратной матрицы по формуле A * A^(-1) = E
    public double[][] proverkaInverseMatrix(double[][]A,double[][]B,int n){
        double[][]res = new double[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                res[i][j]=0;
            }
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                for(int k=0;k<n;k++)
                {                  
                    res[i][j]+=A[i][k]*B[k][j];                   
                }               
            }
        }
        return res;
    }
}
