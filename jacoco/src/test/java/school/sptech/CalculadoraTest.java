package school.sptech;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    void deveSomarDoisNumerosPositivos() {
        Integer resultado = Calculadora.somar(3, 5);
        assertEquals(8, resultado);
    }

    @Test
    void deveSomarNumerosNegativos() {
        Integer resultado = Calculadora.somar(-4, -6);
        assertEquals(-10, resultado);
    }

    @Test
    void deveSomarNumeroPositivoComNegativo() {
        Integer resultado = Calculadora.somar(10, -3);
        assertEquals(7, resultado);
    }

    @Test
    void deveSomarComZero() {
        Integer resultado = Calculadora.somar(0, 7);
        assertEquals(7, resultado);
    }

    @Test
    void deveSomarDoisZeros() {
        Integer resultado = Calculadora.somar(0, 0);
        assertEquals(0, resultado);
    }

    @Test
    void deveLancarExcecaoQuandoPrimeiroParametroForNull() {
        assertThrows(IllegalArgumentException.class, () -> Calculadora.somar(null, 5));
    }

    @Test
    void deveLancarExcecaoQuandoSegundoParametroForNull() {
        assertThrows(IllegalArgumentException.class, () -> Calculadora.somar(3, null));
    }

    @Test
    void deveLancarExcecaoQuandoAmbosParametrosForemNull() {
        assertThrows(IllegalArgumentException.class, () -> Calculadora.somar(null, null));
    }


}