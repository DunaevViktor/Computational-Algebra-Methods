package Lab1VMAGaussMethod;

public class VectorClass {
    int n;
    double[] M;
    //Конструктор класса
    VectorClass(double[] matr, int size){
        n=size;
        M = new double[n];
        for(int i=0;i<n;i++)
        {
            M[i]=matr[i];
        }
    }
    //Функция построения(поиска) вектора
    public VectorClass findVectorMethod (double[][]matrRes,double[][]matr,
    int string, int size, VectorClass[] vc){
        VectorClass res = new VectorClass(matrRes[string],size);  
        for(int j=n-1;j>string;j--)
        {
            for(int i=0;i<n;i++)
            {               
                res.M[i]=res.M[i]-vc[j].M[i]*matr[string][j];               
            }                     
        }
        return res;
    }
    //Функция печати векторов
    public void printVector(){
        for(int i=0;i<n;i++)
        {
            System.out.print(M[i]+" ");
        }
        System.out.println();
    }
    //Функция построения матрицы по векторам
    public double[][] createMatrixFromVectors(VectorClass[] vc, int size){
        double [][]m = new double[size][size];
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                m[i][j]=vc[i].M[j];
            }
        }
        return m;
    }
}
