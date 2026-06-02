public class Calculadora {
    public int somar(Integer a, Integer b) throws IllegalAccessException {
        if(a == null || b == null){
            throw new IllegalAccessException("Operação Inválida");
        }

        return a + b;
    }

//    Somar (Integer a + Integer b) return result

}
