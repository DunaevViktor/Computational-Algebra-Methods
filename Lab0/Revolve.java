package main;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Revolve {
    private double[][] enteredMatrix;
    private double[][] matrix;
    private double[][] ortogon;
    private double[][] eigenmatrix;
    private double[] eigenvalues;
    private double[] residual;
    private double[] maxEigenvector;
    private double maxEigenvalue;
    private double cos;
    private double sin;
    private double maxElement;
    private int iMax;
    private int jMax;
    private int length;
    private int iterationAmount;
    private double kApriori;

    public Revolve() {
        this(5);
    }

    public Revolve(int length) {
        this.length = length;
        iterationAmount = 0;
        maxEigenvalue = 0;
        cos = 0;
        sin = 0;
        maxElement = 0;
        iMax = 0;
        jMax = 0;
        this.enteredMatrix = new double[this.length][this.length];
        this.matrix = new double[this.length][this.length];
        this.eigenmatrix = MyMatrix.copyMatrix(MyMatrix.IDENTITY_MATRIX);
        this.eigenvalues = new double[this.length];
        this.residual = new double[this.length];
        this.maxEigenvector = new double[this.length];
    }

    public Revolve(double[][] matrix) {
        this.length = matrix.length;
        maxEigenvalue = 0;
        cos = 0;
        sin = 0;
        maxElement = 0;
        iMax = 0;
        jMax = 0;
        this.enteredMatrix = matrix;
        this.matrix = MyMatrix.matrixCompositionMatrix(MyMatrix.transposeMatrix(matrix), matrix);
        this.eigenmatrix = MyMatrix.copyMatrix(MyMatrix.IDENTITY_MATRIX);
        this.eigenvalues = new double[this.length];
        this.residual = new double[this.length];
        this.maxEigenvector = new double[this.length];
    }

    public void inputMatrix(String path) throws IOException, ArrayIndexOutOfBoundsException {
        Scanner sc = new Scanner(new File(path));
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                enteredMatrix[i][j] = sc.nextDouble();
            }
        }
        sc.close();
        if (!MyMatrix.isSymmetric(enteredMatrix)) {
            matrix = MyMatrix.matrixCompositionMatrix(MyMatrix.transposeMatrix(enteredMatrix), enteredMatrix);
        } else {
            matrix = MyMatrix.copyMatrix(enteredMatrix);
        }
    }

    private void findMax() throws ArrayIndexOutOfBoundsException {
        maxElement = Math.abs(matrix[0][1]);
        double tmp = 0;
        iMax = 0;
        jMax = 1;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                tmp = Math.abs(matrix[i][j]);
                if (tmp > maxElement) {
                    maxElement = tmp;
                    iMax = i;
                    jMax = j;
                }
            }
        }
        System.out.println("Максимальный элемент в верхней наддиагональной части: " + matrix[iMax][jMax]);
        tmp = 2 * matrix[iMax][jMax] / (matrix[iMax][iMax] - matrix[jMax][jMax]);
        System.out.println("P: " + tmp);
        this.sin = Math.signum(tmp) * Math.sqrt(0.5 * (1 - 1 / Math.sqrt(1 + tmp * tmp)));
        System.out.println("sin: " + this.sin);
        this.cos = Math.sqrt(0.5 * (1 + 1 / Math.sqrt(1 + tmp * tmp)));
        System.out.println("cos: " + this.cos);
    }

    private void findOrtogon() throws ArrayIndexOutOfBoundsException {
        ortogon = MyMatrix.copyMatrix(MyMatrix.IDENTITY_MATRIX);
        ortogon[iMax][iMax] = cos;
        ortogon[jMax][jMax] = cos;
        ortogon[iMax][jMax] = -sin;
        ortogon[jMax][iMax] = sin;
        System.out.println("Ортогональная матрица: ");
        MyMatrix.outputMatrix(ortogon);
    }

    private void findEigenmatrix() throws ArrayIndexOutOfBoundsException {
        eigenmatrix = MyMatrix.matrixCompositionMatrix(eigenmatrix, ortogon);
    }

    private double[] getVectorFromMatrix(double[][] matrix, int col) throws ArrayIndexOutOfBoundsException {
        double[] tmp = new double[length];
        for (int i = 0; i < length; i++) {
            tmp[i] = matrix[i][col];
        }
        return tmp;
    }

    private void findEigenvalues() throws ArrayIndexOutOfBoundsException {
        for (int i = 0; i < length; i++) {
            eigenvalues[i] = matrix[i][i];
        }
    }

    private void findMaxEigenvalue() throws ArrayIndexOutOfBoundsException {
        double max = Math.abs(eigenvalues[0]);
        double tmp = 0;
        int maxIndex = 0;
        for (int i = 0; i < length; i++) {
            tmp = Math.abs(eigenvalues[i]);
            if (tmp > max) {
                max = tmp;
                maxIndex = i;
            }
        }
        maxEigenvalue = eigenvalues[maxIndex];
        maxEigenvector = getVectorFromMatrix(eigenmatrix, maxIndex);
    }

    public void findSolution(double accuracy) throws ArrayIndexOutOfBoundsException {
        System.out.println("A1 = A");
        do {
            System.out.println("Шаг №" + (iterationAmount + 1));
            this.findMax();
            this.findOrtogon();
            this.findEigenmatrix();
            System.out.println("Матрица собственных векторов на " + (iterationAmount + 1) + " шаге");
            MyMatrix.outputMatrix(eigenmatrix);
            matrix = MyMatrix.matrixCompositionMatrix(MyMatrix.transposeMatrix(ortogon), matrix);
            System.out.println("VT*A" + (iterationAmount + 1) + ":");
            MyMatrix.outputMatrix(matrix);
            matrix = MyMatrix.matrixCompositionMatrix(matrix, ortogon);
            System.out.println("(VT*A" + (iterationAmount + 1) +")*V:");
            MyMatrix.outputMatrix(matrix);
            iterationAmount++;
        } while (maxElement >= accuracy);
        System.out.println("Спектр матрицы:");
        MyMatrix.outputMatrix(matrix);
        this.findEigenvalues();
        this.findMaxEigenvalue();
        this.findResidual();
        this.findKApriori(accuracy);
    }

    private void findKApriori(double accuracy) throws ArrayIndexOutOfBoundsException {
        double sum = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != j) {
                    sum += (enteredMatrix[i][j] * enteredMatrix[i][j]);
                }
            }
        }
        kApriori = (Math.log(accuracy) - Math.log(sum)) / Math.log(1.0 - 2.0 / (length * (length - 1)));
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
        residual = Vector.matrixCompositionVector(MyMatrix.matrixCompositionMatrix(MyMatrix.transposeMatrix(enteredMatrix), enteredMatrix), maxEigenvector);
        residual = Vector.vectorMinusVector(residual, Vector.vectorCompositionNumber(maxEigenvector, maxEigenvalue));
    }

    public double[][] getEnteredMatrix() {
        return enteredMatrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[][] getEigenmatrix() {
        return eigenmatrix;
    }

    public double[] getResidual() {
        return residual;
    }

    public double[] getMaxEigenvector() {
        return maxEigenvector;
    }

    public double[] getEigenvalues() {
        return eigenvalues;
    }

    public double getMaxEigenvalue() {
        return maxEigenvalue;
    }

    public int getIterationAmount() {
        return iterationAmount;
    }

    public int getkApriori() {
        return (int) kApriori;
    }

    public int getLength() {
        return length;
    }
}
