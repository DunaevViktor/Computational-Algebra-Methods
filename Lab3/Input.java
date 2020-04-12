package lab3_vma;

import java.io.*;
import java.util.StringTokenizer;
public class Input{
    String a;
    int b;
    FileReader fr;
    BufferedReader br;
    String nameFile;
    Input(String name)
    {
        try
        {
        fr = new FileReader(name);        
        br = new BufferedReader(fr);
        nameFile=name;
        }
        catch (IOException e)
		{
			System.out.println("Ошибка чтения");
		}
    }
     public String stringTake() throws IOException
    {       
        a=new String();
        try
	{
            a=br.readLine();
        }
       catch (IOException e)
		{
			System.out.println("Ошибка чтения с клавиатуры");
		}
        return a;       
    }
    public int sizeTake() throws IOException
    {
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
    public double[] createVector()
    {
        double [] F;int n;
        StringTokenizer st;
        try{
           n = Integer.parseInt(stringTake());
           st=new StringTokenizer(stringTake()," ");
           F = new double[n];
           for(int i=0;i<n;i++)
           {
               F[i] = Double.parseDouble(st.nextToken());
           }
        }
        catch (IOException e)
        {
                System.out.println("Ошибка чтения");
                st=new StringTokenizer(""," ,;");
                F = new double[1];F[0]=0;
        }
        return F;
    }
    public double[][] create2XMatrix ( )
    {
        double [][]M;int n;int m;
        StringTokenizer st;
        try{
        st = new StringTokenizer(stringTake()," ,;");
        n = Integer.parseInt(st.nextToken());
        m=n+1;
        }        
        catch (IOException e)
		{
			System.out.println("Ошибка чтения");
                        st=new StringTokenizer(""," ,;");
                        n=0;m=0;
		}
        M=new double[n][m];       
        try{
        st = new StringTokenizer(stringTake()," ,;");
        }
        catch (IOException e)
        {
                System.out.println("Ошибка чтения");
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
                st = new StringTokenizer(stringTake()," ,;");
                }
                catch (IOException e)
                {
                    System.out.println("Ошибка чтения");
                    st=new StringTokenizer(""," ,.;");
                }
            }
        }
        return M;
    }      
}



