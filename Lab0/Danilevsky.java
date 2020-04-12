package main;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Danilevsky {
    private double[][] matrix;
    private double[][] frobenius;
    private double[][] similarity;
    private double[][] multiply;
    private double[][] inverseMultiply;
    private double[] eigenvector;
    private double eigenvalue;
    private double[] polynomial;
    private double[] residual;
    private int length;

    public Danilevsky(int length) {
        this.length = length;
        eigenvalue = 0;
        matrix = new double[length][length];
        frobenius = new double[length][length];
        eigenvector = new double[length];
        similarity = MyMatrix.copyMatrix(MyMatrix.IDENTITY_MATRIX);
        polynomial = new double[length + 1];
    }

    public void inputMatrix(String path) throws IOException, ArrayIndexOutOfBoundsException {
        Scanner sc = new Scanner(new File(path));
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }
        sc.close();
        System.out.println("Введённая матрица:");
        MyMatrix.outputMatrix(matrix);
        if (!MyMatrix.isSymmetric(matrix)) {
            matrix = MyMatrix.matrixCompositionMatrix(MyMatrix.transposeMatrix(matrix), matrix);
        } else {
            matrix = MyMatrix.copyMatrix(matrix);
        }
        frobenius = MyMatrix.copyMatrix(matrix);
    }

    public void outputResidual() throws ArrayIndexOutOfBoundsException {
        System.out.print("(");
        for (int i = 0; i < length - 1; i++) {
            System.out.printf("%3.1e; ", residual[i]);
        }
        System.out.printf("%3.1e", residual[length - 1]);
        System.out.println(")");
    }

    public void findSolution() throws ArrayIndexOutOfBoundsException {
        this.convertToFrobenius();
        this.findPolynomial();
        this.findEigenvalue();
        this.findEigenvector();
        this.findResidual();
    }

    private void findEigenvector() throws ArrayIndexOutOfBoundsException {
        eigenvector[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            eigenvector[i] = eigenvector[i + 1] * eigenvalue;
        }
        eigenvector = Vector.matrixCompositionVector(similarity, eigenvector);
        eigenvector = Vector.orthonormalize(eigenvector);

    }

    private double funcValue(double arg) throws ArrayIndexOutOfBoundsException {
        double tmp = 0;
        for (int i = 0; i < polynomial.length; i++) {
            tmp += polynomial[i] * Math.pow(arg, i);
        }
        return tmp;
    }

    private void findEigenvalue() {
        Eigenvectors eg = new Eigenvectors(matrix);
        eg.findEigenvalues();
        eigenvalue = eg.getEigenvalue();
    }

    private void convertToFrobenius() throws ArrayIndexOutOfBoundsException {
        System.out.println("Начальная матрица подобия (B = B*Mi):");
        MyMatrix.outputMatrix(similarity);
        for (int i = length - 1; i >= 1; i--) {
            System.out.println("Шаг №" + (length - i));
            /*Annul matrices*/
            inverseMultiply = MyMatrix.copyMatrix(MyMatrix.IDENTITY_MATRIX);
            multiply = MyMatrix.copyMatrix(MyMatrix.IDENTITY_MATRIX);
            /*Find elementary matrices*/
            inverseMultiply = MyMatrix.changeRow(inverseMultiply, frobenius[i], i - 1);
            this.convertMultiply(frobenius[i], i - 1, i - 1);
            System.out.println("Матрица M:");
            MyMatrix.outputMatrix(multiply);
            System.out.println("Матрица M^(-1):");
            MyMatrix.outputMatrix(inverseMultiply);
            /*Similarity matrix*/
            similarity = MyMatrix.matrixCompositionMatrix(similarity, multiply);
            System.out.println("Матрица подобия на " + (length - i) + " шаге:");
            MyMatrix.outputMatrix(similarity);
            /*Multiplying*/
            frobenius = MyMatrix.matrixCompositionMatrix(inverseMultiply, frobenius);
            System.out.println("M^(-1)*A:");
            MyMatrix.outputMatrix(frobenius);
            frobenius = MyMatrix.matrixCompositionMatrix(frobenius, multiply);
            System.out.println("A*M:");
            MyMatrix.outputMatrix(frobenius);
        }
    }

    private void convertMultiply(double[] row, int index, int divider) throws ArrayIndexOutOfBoundsException {
        for (int i = 0; i < length; i++) {
            if (i != index) {
                multiply[index][i] = -row[i] / row[divider];
            } else {
                multiply[index][i] = 1 / row[divider];
            }
        }
    }

    private void findPolynomial() throws ArrayIndexOutOfBoundsException {
        polynomial[polynomial.length - 1] = 1;
        for (int i = 0; i < polynomial.length - 1; i++) {
            polynomial[i] = -frobenius[0][length - i - 1];
        }
    }

    private void findResidual() throws ArrayIndexOutOfBoundsException {
        residual = Vector.matrixCompositionVector(matrix, eigenvector);
        residual = Vector.vectorMinusVector(residual, Vector.vectorCompositionNumber(eigenvector, eigenvalue));
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[][] getFrobenius() {
        return frobenius;
    }

    public double getEigenvalue() {
        return eigenvalue;
    }

    public double[] getPolynomial() {
        return polynomial;
    }

    public double[] getResidual() {
        return residual;
    }

    public int getLength() {
        return length;
    }

    public double[] getEigenvector() {
        return eigenvector;
    }
}