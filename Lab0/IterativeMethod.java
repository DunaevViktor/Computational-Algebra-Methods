package main;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class IterativeMethod {
    private double[][] matrix;
    private double[][] symmetricMatrix;
    private double[] residual;
    private double[] eigenvector;
    private double[] prevEigenvector;
    private double eigenvalue;
    private double prevEigenvalue;
    private int iterationAmount;
    private int length;

    public IterativeMethod(int length) {
        this.length = length;
        this.matrix = new double[this.length][this.length];
        this.symmetricMatrix = new double[this.length][this.length];
        this.residual = new double[this.length];
        this.eigenvector = new double[this.length];
        this.prevEigenvector = new double[this.length];
        this.eigenvalue = 0;
        this.prevEigenvalue = 0;
        this.iterationAmount = 0;
    }

    public void inputMatrix(String path) throws IOException, ArrayIndexOutOfBoundsException {
        Scanner sc = new Scanner(new File(path));
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }
        sc.close();
        if (!MyMatrix.isSymmetric(matrix)) {
            symmetricMatrix = MyMatrix.matrixCompositionMatrix(MyMatrix.transposeMatrix(matrix), matrix);
        } else {
            symmetricMatrix = MyMatrix.copyMatrix(matrix);
        }
    }

    public void outputResidual() throws ArrayIndexOutOfBoundsException {
        System.out.print("(");
        for (int i = 0; i < length - 1; i++) {
            System.out.printf("%3.1e; ", residual[i]);
        }
        System.out.printf("%3.1e", residual[length - 1]);
        System.out.println(")");
    }

    private void findResidual() throws ArrayIndexOutOfBoundsException {
        residual = Vector.matrixCompositionVector(symmetricMatrix, eigenvector);
        residual = Vector.vectorMinusVector(residual, Vector.vectorCompositionNumber(eigenvector, eigenvalue));
    }

    private void initialValueForEigenvector() throws ArrayIndexOutOfBoundsException {
        for (int i = 0; i < eigenvector.length; i++) {
            eigenvector[i] = 1;
        }
    }

    private void findEigenvector() throws ArrayIndexOutOfBoundsException {
        prevEigenvector = Vector.copyVector(eigenvector);
        eigenvector = Vector.matrixCompositionVector(symmetricMatrix, eigenvector);
    }

    private void findEigenvalue() throws ArrayIndexOutOfBoundsException {
        prevEigenvalue = eigenvalue;
        eigenvalue = eigenvector[0] / prevEigenvector[0];
    }

    public void findSolution(double accuracy) throws ArrayIndexOutOfBoundsException {
        this.initialValueForEigenvector();
        do {
            System.out.println("Шаг №" + (iterationAmount + 1));
            this.findEigenvector();
            System.out.print("A*X:");
            Vector.outputVector(eigenvector);
            this.findEigenvalue();
            System.out.println("Лямбда на " + (iterationAmount + 1) + " шаге: " + eigenvalue);
            iterationAmount++;
            System.out.println("Math.abs(eigenvalue - prevEigenvalue): " + Math.abs(eigenvalue - prevEigenvalue));
        } while (Math.abs(eigenvalue - prevEigenvalue) >= accuracy);
        eigenvector = Vector.orthonormalize(eigenvector);
        this.findResidual();
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[][] getSymmetricMatrix() {
        return symmetricMatrix;
    }

    public double[] getResidual() {
        return residual;
    }

    public double[] getEigenvector() {
        return eigenvector;
    }

    public double getEigenvalue() {
        return eigenvalue;
    }

    public int getLength() {
        return length;
    }

    public int getIterationAmount() {
        return iterationAmount;
    }
}
