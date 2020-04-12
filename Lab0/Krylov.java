package main;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Krylov {
    private int length;
    private double[][] matrix;
    private double[][] symmetricMatrix;
    private double[][] sharedMatrix;
    private double[] polynomial;
    private double[] residual;
    private double[] eigenvector;
    private double eigenvalue;
    private double[] coefficientsForY;

    public Krylov(int length) {
        this.length = length;
        matrix = new double[length][length];
        symmetricMatrix = new double[length][length];
        sharedMatrix = new double[length][length + 1];
        polynomial = new double[length + 1];
        residual = new double[length];
        eigenvector = new double[length];
        eigenvalue = 0;
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

    public void findSolution() throws ArrayIndexOutOfBoundsException {
        this.convertToEquationMatrix();
        this.findPolynomial();
        this.findEigenvalue();
        this.findEigenvector();
        this.findResidual();
    }

    private void findEigenvector() throws ArrayIndexOutOfBoundsException {
        /*Looking for coefficients for vectorsY*/
        double[] coefficients = new double[length];
        coefficients[0] = 1;
        for (int i = 1; i < coefficients.length; i++) {
            coefficients[i] = coefficients[i - 1] * eigenvalue + polynomial[i];
        }
        /*Rolling around*/
        double[][] vectorsY = new double[length][length];
        for (int i = 0; i < vectorsY.length; i++) {
            for (int j = 0; j < vectorsY[0].length; j++) {
                vectorsY[j][i] = sharedMatrix[i][j];
            }
        }
        coefficientsForY = Vector.copyVector(coefficients);
        /*Looking for eigenvector*/
        for (int i = 0; i < eigenvector.length; i++) {
            eigenvector = Vector.vectorPlusVector(Vector.vectorCompositionNumber(vectorsY[i], coefficients[i]), eigenvector);
        }
        eigenvector = Vector.orthonormalize(eigenvector);
    }

    private void findPolynomial() throws ArrayIndexOutOfBoundsException {
        /*Find coefficients*/
        MSquareRoot msr = new MSquareRoot(sharedMatrix);
        msr.findSolution();
        double[] coefficients = msr.getAnswers();
        /*Fill polynomial*/
        polynomial[0] = 1;
        for (int i = 1; i < polynomial.length; i++) {
            polynomial[i] = -coefficients[i - 1];
        }
    }

    private double funcValue(double arg) throws ArrayIndexOutOfBoundsException {
        double tmp = 0;
        for (int i = 0; i < polynomial.length; i++) {
            tmp += polynomial[i] * Math.pow(arg, i);
        }
        return tmp;
    }

    private void findEigenvalue() {
        Eigenvectors eg = new Eigenvectors(symmetricMatrix);
        eg.findEigenvalues();
        eigenvalue = eg.getEigenvalue();
    }

    private void findResidual() throws ArrayIndexOutOfBoundsException {
        residual = Vector.matrixCompositionVector(symmetricMatrix, eigenvector);
        residual = Vector.vectorMinusVector(residual, Vector.vectorCompositionNumber(eigenvector, eigenvalue));
    }

    private void convertToEquationMatrix() throws ArrayIndexOutOfBoundsException {
        double[] vector = new double[length];
        vector[0] = 1;
        for (int i = length - 1; i >= 0; i--) {
            putVectorToEquationMatrix(vector, i);
            vector = Vector.matrixCompositionVector(symmetricMatrix, vector);
        }
        putVectorToEquationMatrix(vector, length);
    }

    private void putVectorToEquationMatrix(double[] vector, int col) throws ArrayIndexOutOfBoundsException {
        for (int i = 0; i < length; i++) {
            sharedMatrix[i][col] = vector[i];
        }
    }

    public int getLength() {
        return length;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] getPolynomial() {
        return polynomial;
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

    public double[][] getSharedMatrix() {
        return sharedMatrix;
    }

    public double[][] getSymmetricMatrix() {
        return symmetricMatrix;
    }

    public double[] getCoefficientsForY() {
        return coefficientsForY;
    }
}
