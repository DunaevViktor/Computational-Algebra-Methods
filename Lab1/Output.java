
package lab1vma;
import java.text.NumberFormat;

public class Output {
    int stop;
    NumberFormat formatter = NumberFormat.getNumberInstance();  
    //Функция вывода матрицы
    public void outMatrix(double [][]matrix,int n,int m,int size){
        stop=size;
        formatter.setMaximumFractionDigits(stop);
        for(int i=0;i<n;i++)
        {
             for(int j=0;j<m;j++)
             {
                 System.out.print(formatter.format(matrix[i][j])+ "\t");
             }
             System.out.println();
        }
    }  
    
}
