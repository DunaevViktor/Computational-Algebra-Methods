package lab1vma;
import java.io.*;
import java.util.StringTokenizer;

public class Input {
     String a;
    int b;
    FileReader fr;
    BufferedReader br;
    String nameFile;
    //Функция инициализации файла
    Input(String name){
        try
        {
        fr = new FileReader(name);        
        br = new BufferedReader(fr);
        nameFile=name;
        }
        catch (IOException e)
		{
			System.out.println("Ошибка чтения!");
		}
    }
    //Функция чтение строки
    public String stringRead() throws IOException{       
        a=new String();
        try
	{
            a=br.readLine();
        }
       catch (IOException e)
		{
			System.out.println("Ошибка чтения с клавиатуры!");
		}
        return a;
    }
    //Функция чтения размерности
   public int sizeRead() throws IOException{
        b=0;       
        try
	{
        String line = br.readLine();
        b = Integer.parseInt(line);     
        }
        catch (NumberFormatException e)                   
                {
			System.out.println("Не целое число");
		}
		catch (IOException e)
		{
			System.out.println("Ошибка чтения с клавиатуры");
		}
        return b;
    }
    //Функция создания матрицы
    public double[][] createMatrix ( int n, int m){
        double [][]M;
        M=new double[n][m];
        StringTokenizer st;
        try{
        st = new StringTokenizer(stringRead()," ,;");
        }
        catch (IOException e)
		{
			System.out.println("Ошибка чтения!");
                        st=new StringTokenizer(""," ,;");
		}
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<m;j++)
            {
                if(st.hasMoreTokens())
                {                                              
                M[i][j]=Double.parseDouble(st.nextToken());
                }
                else
                {
                M[i][j]=0;
                }
            }
            if(i<n-1)
            {
                try{ 
                st = new StringTokenizer(stringRead()," ,;");
                }
                catch (IOException e)
                {
                    System.out.println("Ошибка чтения!");
                    st=new StringTokenizer(""," ,.;");
                }
            }
        }
        return M;
    }
    //Функция копирования матрицы
    public double[][] copyMatrix (double[][] M, int n, int m){
        double[][] N = new double[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                N[i][j]=M[i][j];
            }
        }
        return N;
    }
}
