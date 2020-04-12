package lab2_koren;

import java.text.NumberFormat;

public class Output {
    void Output(double[][]ma1,int n1, int m1){
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(5);
        for(int i = 0; i < n1; ++i) {
            for(int j = 0; j < m1; ++j) {
                if(ma1[i][j]==0) System.out.print("0.0000      ");
                else System.out.print(formatter.format(ma1[i][j]) + "      ");
                //System.out.print(ma1[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    void OutputX(double[]x, int n){
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(5);
        for(int j = 0; j < n; ++j) {
                System.out.print("x"+(j+1)+" = "+formatter.format(x[j]) + "\n");
        }
    }
    void OutputVector(double[]x, int n){
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(5);
        for(int j = 0; j < n; ++j) {
                System.out.print(formatter.format(x[j]) + "\n");
        }
    }
}

