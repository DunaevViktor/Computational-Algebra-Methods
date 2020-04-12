package main;
import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class Eigenvectors {
    private double[][] matrix;
    private double eigenvalue;

    public Eigenvectors(double[][] matrix) {
        this.matrix = matrix;
    }

    public void findEigenvalues() {
        EigenvalueDecomposition ed = new EigenvalueDecomposition(Matrix.constructWithCopy(matrix));
        double[] realParts = ed.getRealEigenvalues();
        double[] imageParts = ed.getImagEigenvalues();
        /*Find max*/
        /*double max = Math.abs(realParts[0]);
        int index = 0;
        for (int i = 0; i < realParts.length; i++) {
            if (Math.abs(realParts[i]) > max && imageParts[i] == 0) {
                max = Math.abs(realParts[i]);
                index = i;
            }
        }
        eigenvalue = realParts[index];*/
        eigenvalue = realParts[realParts.length - 1];
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double getEigenvalue() {
        return eigenvalue;
    }
}
