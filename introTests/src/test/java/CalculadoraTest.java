import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import provider.SomaProvider;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @ParameterizedTest
    @ArgumentsSource(SomaProvider.class)
    @DisplayName("Plus when triggered with 2 and 2 should return 4")
    void plusWhenTriggeredWith2and2ShouldReturn4(
            Integer primeiroNumero,
            Integer segundoNumero,
            Integer resultadoEsperado
    ) throws IllegalAccessException {
        Calculadora calculadora = new Calculadora();

        int resultado = calculadora.somar(primeiroNumero, segundoNumero);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    @DisplayName("It should throw exception when invoked with nullable values")
    void shouldPostExceptionWhenInvokedWithNullableValues(){
        Calculadora calculadora = new Calculadora();

        Integer primeiroNumero = null;
        Integer segundoNumero = 10;
        var mensagemEsperada = "Operação Inválida";

//        int resultadoObtido = calculadora.somar(primeiroNumero, segundoNumero);

        IllegalAccessException illegalAccessException = assertThrows(
                IllegalAccessException.class,
                () -> calculadora.somar(primeiroNumero, segundoNumero)
        );

        String receivedMessage = illegalAccessException.getMessage();

        assertEquals(mensagemEsperada, receivedMessage);
    }
}