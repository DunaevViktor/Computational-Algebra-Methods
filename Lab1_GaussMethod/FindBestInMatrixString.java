package Lab1VMAGaussMethod;

public class FindBestInMatrixString {
    int kol_vo;
    //Констурктор класса
    FindBestInMatrixString(){
        kol_vo=0;
    }
    //Функция поиска главного элемента по строке
    public double[][] findBest(double[][]M,int str,int[] A,int n){
        double best=M[str][str]; int num=str;
        for(int i=str;i<n;i++)
        { 
            if(Math.abs(best) < Math.abs(M[str][i]))
            {             
                best=M[str][i]; num = i;           
            }           
        }        
        double temp;
        for(int i=0;i<n;i++)
        {
          temp = M[i][str];
          M[i][str] = M[i][num];
          M[i][num] = temp;
        }
        int temp2;
        if(A[str]!=A[num])
        {
            kol_vo++;
        }
        temp2=A[str];
        A[str]=A[num];
        A[num]=temp2;
        return M;
    }
}
