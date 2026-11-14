package br.ufam.jenkins;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConversorTemperaturaTest {

    private static final double DELTA = 0.001;

    // ---------- Fahrenheit -> Celsius ----------

    @Test
    @DisplayName("212 F (ebulicao da agua) = 100 C")
    void fahrenheitParaCelsius_pontoDeEbulicao() {
        assertEquals(100.0, ConversorTemperatura.fahrenheitParaCelsius(212.0), DELTA);
    }

    @Test
    @DisplayName("32 F (congelamento da agua) = 0 C")
    void fahrenheitParaCelsius_pontoDeCongelamento() {
        assertEquals(0.0, ConversorTemperatura.fahrenheitParaCelsius(32.0), DELTA);
    }

    @Test
    @DisplayName("-40 F = -40 C (ponto onde as escalas se encontram)")
    void fahrenheitParaCelsius_menos40() {
        assertEquals(-40.0, ConversorTemperatura.fahrenheitParaCelsius(-40.0), DELTA);
    }

    // ---------- Celsius -> Fahrenheit ----------

    @Test
    @DisplayName("0 C = 32 F")
    void celsiusParaFahrenheit_zero() {
        assertEquals(32.0, ConversorTemperatura.celsiusParaFahrenheit(0.0), DELTA);
    }

    @Test
    @DisplayName("100 C = 212 F")
    void celsiusParaFahrenheit_cem() {
        assertEquals(212.0, ConversorTemperatura.celsiusParaFahrenheit(100.0), DELTA);
    }

    @Test
    @DisplayName("37 C (temperatura corporal) = 98.6 F")
    void celsiusParaFahrenheit_temperaturaCorporal() {
        assertEquals(98.6, ConversorTemperatura.celsiusParaFahrenheit(37.0), DELTA);
    }

    // ---------- Consistencia entre os dois metodos ----------

    @Test
    @DisplayName("Ida e volta (C -> F -> C) devolve o valor original")
    void idaEVolta() {
        double original = 25.5;
        double volta = ConversorTemperatura.fahrenheitParaCelsius(
                ConversorTemperatura.celsiusParaFahrenheit(original));
        assertEquals(original, volta, DELTA);
    }
}
