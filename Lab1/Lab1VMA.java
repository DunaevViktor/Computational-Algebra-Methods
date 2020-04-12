package lab1vma;
import java.io.*;
import java.text.NumberFormat;

public class Lab1VMA {
    public static void main(String[] args) {
        Input I = new Input("InputFile.txt");
        FindBestElements F = new FindBestElements();
        OneSplittingScheme S = new OneSplittingScheme();
        FindResults C = new FindResults(); Output O = new Output();   
        int stop =5;double det;double []nevVekt;  double []X ;  double [][]EMatr;   
        int n;int m;double [][]matrix;int[] A; double [][]copyMatrix;double [][]resultMatrix;
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(stop);
        try
        {
            n=I.sizeRead();
            m=I.sizeRead();   
        }
        catch (IOException e)
        {
            System.out.println("Ошибка чтения!");
            n=1;m=1;
        }
        matrix=I.createMatrix(n, m);
        copyMatrix = I.copyMatrix(matrix, n, m);        
        A = new int[n];
        for(int i=0;i<n;i++)
        {
           A[i]=i;
        }        
        for(int i=0;i<n;i++)
        {
        matrix=F.findBest(matrix, i, A, n);     
        matrix = S.split(matrix, i, n);
        }
        X = S.findVectorX(matrix, n);
        System.out.println("Вектор X: ");    
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(A[j]==i)
                {
                   System.out.print(formatter.format(X[A[j]])+" "); 
                }
            }
        }
        System.out.println();       
        System.out.println("Вектор невязки: ");
        nevVekt = S.check(copyMatrix, n, X, A);        
        for(int i=0;i<n;i++)
        {
         System.out.print(nevVekt[i]+" ");
        }
        System.out.println();
        resultMatrix = C.findInverseMatrix(copyMatrix, n);
        System.out.println("Обратная матрица: ");
        O.outMatrix(resultMatrix, n, n,10);
        det = Math.pow(-1, F.kol_vo);
        for(int i=0;i<n;i++)
        {
         det = det*X[i];
        }
        System.out.println("Определитель = "+ det);
        EMatr = C.checkInverseMatrix(copyMatrix, resultMatrix, n);
        System.out.println("Проверка обратной матрицы: A * A^(-1) = E");
        O.outMatrix(EMatr, n, n,20);
        }        
    }
