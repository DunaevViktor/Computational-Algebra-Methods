package lab3_vma;

import java.text.NumberFormat;
public class Lab3_VMA {
    
    public static void main(String[] args) {
        int stop = 6;
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(stop);
        Input I = new Input("input.txt");
        Podschet P = new Podschet();
        Methods M = new Methods();
        double E = 0.00001;
        double [][] matr = I.create2XMatrix();
        double[] F = I.createVector();
        System.out.println("Исходная матрица");
        for(int i=0;i<matr.length;i++)
        {
            for(int j=0;j<matr.length;j++)
            {
                System.out.print(matr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("Исходный вектор f");
        for(int i=0;i<F.length;i++)
        {
            System.out.print(F[i]+" ");
        }
        System.out.println();
        double[][] matrT = P.getTranspMatrix(matr);     
        double[][]ATA = P.getATAMatrix(matr, matrT);
        double ATAnorma = P.getMatrixNorma(ATA);
        double[][]B=P.getMatrixB(ATA, ATAnorma);
        System.out.println("B матрица");
        for(int i=0;i<B.length;i++)
        {
            for(int j=0;j<B.length;j++)
            {
                System.out.print(formatter.format(B[i][j])+" ");
            }
            System.out.println();
        }
       double[]G = P.getVectorG(F, ATAnorma,matr);
       System.out.println("Вектор G");
        for(int i=0;i<G.length;i++)
        {
            System.out.print(formatter.format(G[i])+" ");
        }
        System.out.println();
        double[]iterX = M.iterationMethod(B, G, E);
       
        double[]XZeid = M.methodZeidelia(B, G, E);
        int max = P.getMaxIter(B, G, E);
        System.out.println("Априорное количество итераций для"
                + " методов Якоби и Гаусса-Зейделя = "+ max);
        double[] XYakobi = M.methodYakobi(matr, F, E);      
        double[] XGZ = M.methodGZ(matr, F, E);
        System.out.println("Вектор невязки для метода простой итерации:");
        P.vectorPrint(P.vectorNev(matr, F, iterX));
        System.out.println("Вектор невязки для метода Зейделя:");
        P.vectorPrint(P.vectorNev(matr, F, XZeid));
        System.out.println("Вектор невязки для метода Якоби:");
        P.vectorPrint(P.vectorNev(matr, F, XYakobi));
        System.out.println("Вектор невязки для метода Гаусса-Зейделя:");
        P.vectorPrint(P.vectorNev(matr, F, XGZ));    
    }
}
    
