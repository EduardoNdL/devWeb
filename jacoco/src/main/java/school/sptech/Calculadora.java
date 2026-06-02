package school.sptech;

public class Calculadora {

    public static Integer somar(Integer n1, Integer n2) {
        if (n1 == null || n2 == null) {
            throw new IllegalArgumentException();
        }
        return n1 + n2;
    }
}
