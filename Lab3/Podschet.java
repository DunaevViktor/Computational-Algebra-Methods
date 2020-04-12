package lab3_vma;

import java.text.NumberFormat;
public class Podschet {
int stop = 6; NumberFormat formatter = NumberFormat.getNumberInstance();       
    public double[][] getTranspMatrix (double[][]A)
    {
        double[][]B = new double[A.length][A.length];
        double [][]C = new double[A.length][A.length];
        for(int i=0;i<C.length;i++)
        {
            for(int j=0;j<C.length;j++)
            {
                C[i][j]=0;
            }
        }
        for(int i=0;i<A.length;i++)
        {
            for(int j=0;j<A.length;j++)
            {
                B[i][j]=A[j][i];
            }
        }
        
        return B;
    }
    public double[][] getATAMatrix(double[][]A,double[][]AT)
    {
        double[][]res = new double[A.length][A.length];
        for(int i =0;i<A.length;i++)
        {
            for(int j=0;j<A.length;j++)
            {
                res[i][j]=0;
                for(int k=0;k<A.length;k++)
                {
                    res[i][j]+=AT[i][k]*A[k][j];
                }
            }
        }
        return res;
    }
    public double getMatrixNorma(double[][]A)
    {
        double res=0;double tempRes=0;
        for(int i=0;i<A.length;i++)
        {
            res+=Math.abs(A[0][i]);
        }
        for(int i=1;i<A.length;i++)
        {
          tempRes=0;
          for(int j=0;j<A.length;j++)
          {
              tempRes+=Math.abs(A[i][j]);
          }
          if(tempRes>res)
          {
              res = tempRes;
          }
        }
        return res;
    }
    public double[][]getMatrixB(double[][]ATA,double norma)
    {
       double[][]tempMatr = new double[ATA.length][ATA.length];
       for(int i=0;i<ATA.length;i++)
       {
           for(int j=0;j<ATA.length;j++)
           {
               tempMatr[i][j]=ATA[i][j]/norma;
           }
       }
       double[][]B = new double[ATA.length][ATA.length];
       for(int i=0;i<ATA.length;i++)
       {
           for(int j=0;j<ATA.length;j++)
           {
               if(i==j)
               {
                   B[i][j]=1-tempMatr[i][j];
               }
               else
               {
                   B[i][j]=-tempMatr[i][j];
               }
           }
       }
       return B;
    }
    public double[] getVectorG (double[] F, double ATAnorma,double[][]matr)
    {
        double[] res = new double[F.length];
        for(int i=0;i<F.length;i++)
        {
            res[i]=0;
            for(int j=0;j<F.length;j++)
            {
               res[i]=res[i]+matr[j][i]*F[j];
            }
        }
        for(int i=0;i<F.length;i++)
        {
            res[i]=res[i]/ATAnorma;
        }
        return res;
    }
    public int getMaxIter(double[][]B,double[]g,double E)
    {
        double normaB;double normaG;
        double tempNorma=0;int k;
        for(int i=0;i<B.length;i++)
        {
            tempNorma = tempNorma+B[0][i];
        }
        normaB=tempNorma;
        for(int i=1;i<B.length;i++)
        {
            tempNorma=0;
            for(int j=0;j<B.length;j++)
            {
                tempNorma=tempNorma+B[i][j];
            }
            if(tempNorma>normaB)
            {
                normaB=tempNorma;
            }
        }
        normaG=g[0];
        for(int i=1;i<g.length;i++)
        {
            if(g[i]>normaG)
            {
                normaG=g[i];
            }
        }
        double firstLogElem=E*(1-normaB)/normaG;
       k = (int)((Math.log(firstLogElem)/Math.log(normaB))+1);
       return k;       
    }
    public double[] vectorNev (double[][]matr,double[]F,double[]X)
    {
        double[] vector = new double[X.length];
        for(int i=0;i<X.length;i++)
        {
            vector[i]=0;
            for(int j=0;j<X.length;j++)
            {
                vector[i]=vector[i]+matr[i][j]*X[j];
            }
            vector[i]=vector[i]-F[i];
        }
        return vector;
    }
    public void vectorPrint(double[] X)
    {
        formatter.setMaximumFractionDigits(stop);
        for(int i=0;i<X.length;i++)
        {
            System.out.print(formatter.format(X[i])+" ");
        }
        System.out.println();
    }
}

