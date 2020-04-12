package lab2_koren;

public class Lab2_Koren {

    public static void main(String[] args) {
       
        InputFile IF = new InputFile("Input.txt");
        Output out = new Output();
        Matrix ma = new Matrix();
        Umnozit mul = new Umnozit();
        Vector v = new Vector();
        int n = IF.intTake();
        int m = IF.intTake();
        double[][] afull = IF.create2XMatrix(n, m);
        double[] b = new double[n];
        double[][] a = new double[n][n];
        for(int i=0;i<n;i++)
        for(int j=0;j<n;j++){
            a[i][j]=afull[i][j];}
        for(int i=0;i<n;i++){
            b[i]=afull[i][m-1];}
        System.out.println("Source matrix A: ");
        out.Output(a, n, n);
        System.out.println("Source vector B: ");
        out.OutputVector(b,n);
        b = mul.doItWithVect(ma.transpored(a), b);
        a = mul.doIt(ma.transpored(a), a);
        System.out.println("Matrix A(t)*A: ");
        out.Output(a, n, n);
        System.out.println("Vector A(t)*B: ");
        out.OutputVector(b,n);
        double[] d = null;
        double[][]S = ma.findS(a);
        double[] Y = ma.findY(S, b);
        double[]X = ma.findX(S, Y);
        System.out.println("Matrix S: ");
        out.Output(S, n, n);
        System.out.println("Vector Y: ");
        out.OutputVector(Y,n);
        System.out.println("Vector X: ");
        out.OutputVector(X,n);
        System.out.println();
        double det = ma.findDet(S);
        System.out.println("Determinant: "+ det);
        //double[]r = null;
        //r = v.VectorR(a, n, X);
        //out.OutputVector(r, n);
    }
}
