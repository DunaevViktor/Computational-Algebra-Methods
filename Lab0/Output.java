package main;
public class Output {
    public static void revolveOutput(Revolve revolve) throws ArrayIndexOutOfBoundsException {
        System.out.println("Результаты:");
        System.out.println("Введённая матрица:");
        MyMatrix.outputMatrix(revolve.getEnteredMatrix());
        System.out.println("Матрица собственных векторов:");
        MyMatrix.outputMatrix(revolve.getEigenmatrix());
        System.out.print("Вектор собственных значений: ");
        Vector.outputVector(revolve.getEigenvalues());
        System.out.println("Количество итераций: " + revolve.getIterationAmount());
        System.out.println("k-априорное: " + revolve.getkApriori());
        System.out.println("Максимальное собственное значение: " + revolve.getMaxEigenvalue());
        System.out.print("Собственный вектор (max) (метод вращений): ");
        Vector.outputVector(revolve.getMaxEigenvector());
    }

    public static void krylovOutput(Krylov krylov) throws ArrayIndexOutOfBoundsException {
        System.out.println("Введённая матрица:");
        MyMatrix.outputMatrix(krylov.getMatrix());
        System.out.println("Симметрическая матрица:");
        MyMatrix.outputMatrix(krylov.getSymmetricMatrix());
        System.out.println("Расширенная матрица из векторов:");
        MyMatrix.outputMatrix(krylov.getSharedMatrix());
        System.out.print("Вектор из коэффициентов многочлена:");
        Vector.outputVector(krylov.getPolynomial());
        System.out.print("Коэффициенты для векторов y:");
        Vector.outputVector(krylov.getCoefficientsForY());
        System.out.printf("Максимальное собственное значения: %6.5f%n", krylov.getEigenvalue());
        System.out.print("Собственный вектор (по методу Крылова): ");
        Vector.outputVector(krylov.getEigenvector());
    }

    public static void iterativeOutput(IterativeMethod iterativeMethod) throws ArrayIndexOutOfBoundsException{
        System.out.println("Результаты:");
        System.out.println("Введённая матрица:");
        MyMatrix.outputMatrix(iterativeMethod.getMatrix());
        System.out.println("Симметрическая матрица:");
        MyMatrix.outputMatrix(iterativeMethod.getSymmetricMatrix());
        System.out.printf("Максимальное собственное значения (по степенному методу): %1.15f%n", iterativeMethod.getEigenvalue());
        System.out.print("Собственный вектор (по степенному методу): ");
        Vector.outputVector(iterativeMethod.getEigenvector());
        System.out.println("Количество итераций: " + iterativeMethod.getIterationAmount());
    }

    public static void danilevskyOutput(Danilevsky danilevsky) throws ArrayIndexOutOfBoundsException{
        System.out.println("Симметрическая матрица:");
        MyMatrix.outputMatrix(danilevsky.getMatrix());
        System.out.println("Форма Фробениуса:");
        MyMatrix.outputMatrix(danilevsky.getFrobenius());
        System.out.printf("Максимальное собственное значение: %6.5f%n", danilevsky.getEigenvalue());
        System.out.print("Собственный вектор, соответствующий максимальному с.з.: ");
        Vector.outputVector(danilevsky.getEigenvector());
    }
}
