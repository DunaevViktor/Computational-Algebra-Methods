package main;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            /*Вращения*/
            System.out.println("Метод вращений:");
            Revolve revolve = new Revolve(3);
            revolve.inputMatrix("Matrix.txt");
            revolve.findSolution(0.1);
            Output.revolveOutput(revolve);
            /*Крылов*/
            System.out.println();
            Krylov krylov = new Krylov(3);
            krylov.inputMatrix("Matrix.txt");
            System.out.println("Метод Крылова:");
            krylov.findSolution();
            Output.krylovOutput(krylov);
            /*Степенной метод*/
            System.out.println();
            System.out.println("Степенной метод:");
            IterativeMethod iterativeMethod = new IterativeMethod(3);
            iterativeMethod.inputMatrix("Matrix.txt");
            iterativeMethod.findSolution(0.1);
            Output.iterativeOutput(iterativeMethod);
            /*Данилевский*/
            System.out.println();
            System.out.println("Метод Данилевского:");
            Danilevsky danilevsky = new Danilevsky(3);
            danilevsky.inputMatrix("Matrix.txt");
            danilevsky.findSolution();
            Output.danilevskyOutput(danilevsky);
        } catch (IOException e) {
            System.out.println("IOException is thrown.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException is thrown.");
        }
    }
}
