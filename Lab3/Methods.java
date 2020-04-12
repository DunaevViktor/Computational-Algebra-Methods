package lab3_vma;

import java.text.NumberFormat;
public class Methods {
    int stop = 6;
    NumberFormat formatter = NumberFormat.getNumberInstance();    
  public double[] iterationMethod(double[][]B,double[]G,double E){
      formatter.setMaximumFractionDigits(stop);
      double[] X = new double[G.length];double[]Xtemp = new double[G.length];
      for(int i=0;i<X.length;i++)
      {
          X[i]=G[i];
      }
      int microAdd;int iterNum=0;
      do{
          microAdd=0;
          for(int i=0;i<X.length;i++)
          {
              Xtemp[i]=0;
              for(int j=0;j<X.length;j++)
              {                
                  Xtemp[i]=Xtemp[i]+B[i][j]*X[j];
              }            
              Xtemp[i]=Xtemp[i]+G[i];           
              if(Math.abs(Xtemp[i]-X[i])<=E)
              {
                  microAdd++;
              }
          }
          for(int i=0;i<X.length;i++)
          {
              X[i]=Xtemp[i];            
          }  
          iterNum++;
      }while(microAdd<G.length);
      System.out.println("Результат метода простой итерации");
      for(int i=0;i<X.length;i++)
      {
          System.out.print(formatter.format(X[i])+" ");
      }
      System.out.println();
      System.out.println("Количество итераций = "+iterNum);
      return X;
  }
  public double[] methodZeidelia(double[][]B,double[]G,double E){
      formatter.setMaximumFractionDigits(stop);
      double[] X = new double[G.length];double[]Xtemp = new double[G.length];
      for(int i=0;i<X.length;i++)
      {
          X[i]=G[i];
      }
      int microAdd;int iterNum=0;
      do{
          microAdd=0;
          for(int i=0;i<X.length;i++)
          {
              Xtemp[i]=0;
              for(int j=0;j<X.length;j++)
              {                
                  if(i<j)
                  {
                      Xtemp[i]=Xtemp[i]+B[i][j]*Xtemp[j];
                  }
                  else
                  {
                      Xtemp[i]=Xtemp[i]+B[i][j]*X[j];
                  }
              }            
              Xtemp[i]=Xtemp[i]+G[i];           
              if(Math.abs(Xtemp[i]-X[i])<=E)
              {
                  microAdd++;
              }
          }
          for(int i=0;i<X.length;i++)
          {
              X[i]=Xtemp[i];            
          } 
          iterNum++;
      }while(microAdd<G.length);
       System.out.println("Результат метода Зейделя");
      for(int i=0;i<X.length;i++)
      {
          System.out.print(formatter.format(X[i])+" ");
      }
      System.out.println();
      System.out.println("Количество итераций = "+iterNum);
      return X;
  }
 public double[] methodYakobi ( double[][]matr,double[]F,double E)    {
     formatter.setMaximumFractionDigits(stop);
     double[]X = new double[F.length];
     double[]Xtemp = new double[F.length];
     for(int i=0;i<X.length;i++)
     {
         X[i]=F[i];
     }
     int microAdd;int iterationNum=0;
     do
     {
         microAdd=0;
         for(int i=0;i<X.length;i++)
         {
             Xtemp[i]=F[i]/matr[i][i];
             for(int j=0;j<X.length;j++)
             {
                 if(i!=j)
                 {
                 Xtemp[i]=Xtemp[i]+(-1)*(matr[i][j]/matr[i][i])*X[j];
                 }
             }
             if(Math.abs(Xtemp[i]-X[i])<E)
             {
                 microAdd++;
             }
         }
         for(int i=0;i<X.length;i++)
         {
             X[i]=Xtemp[i];
         }
         iterationNum++;
     }while(microAdd<X.length);
     System.out.println();
     System.out.println("Результат метода Якоби:");
     for(int i=0;i<X.length;i++)
     {
         System.out.print(formatter.format(X[i])+" ");
     }
     System.out.println();
     System.out.println("Количество итераций = " + iterationNum);   
     return X;
 }
 public double[] methodGZ ( double[][]matr,double[]F,double E)    {
     formatter.setMaximumFractionDigits(stop);
     double[]X = new double[F.length];
     double[]Xtemp = new double[F.length];
     for(int i=0;i<X.length;i++)
     {
         X[i]=F[i];
     }
     int microAdd;int iterationNum=0;
     do
     {
         microAdd=0;
         for(int i=0;i<X.length;i++)
         {
             Xtemp[i]=F[i]/matr[i][i];           
             for(int j=0;j<X.length;j++)
             {
                 if(i!=j)
                 {
                     if(i<j)
                     {
                     Xtemp[i]=Xtemp[i]+(-1)*(matr[i][j]/matr[i][i])*Xtemp[j];
                     }
                     else
                     {
                     Xtemp[i]=Xtemp[i]+(-1)*(matr[i][j]/matr[i][i])*X[j];   
                     }
                 }
             } 
             if(Math.abs(Xtemp[i]-X[i])<E)
             {
                 microAdd++;
             }
         }
         for(int i=0;i<X.length;i++)
         {
             X[i]=Xtemp[i]; 
         }
         iterationNum++; 
     }while(microAdd<X.length);
     System.out.println("Результат метода Гаусса-Зейделя");
     for(int i=0;i<X.length;i++)
     {
         System.out.print(formatter.format(X[i])+" ");
     }
     System.out.println();
     System.out.println("Количество итераций = "+ iterationNum);
     return X;
 }
}
