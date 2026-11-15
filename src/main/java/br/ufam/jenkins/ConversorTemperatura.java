package br.ufam.jenkins;

/**
 * Conversor simples de temperatura entre Fahrenheit e Celsius.
 * Usado como alvo de build/teste no pipeline do Jenkins.
 */
public final class ConversorTemperatura {

    private ConversorTemperatura() {
        // classe utilitaria: nao deve ser instanciada
    }

    /** Converte Fahrenheit (F) para Celsius (C): C = (F - 32) * 5 / 9 */
    public static double fahrenheitParaCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0
    }

    /** Converte Celsius (C) para Fahrenheit (F): F = C * 9 / 5 + 32 */
    public static double celsiusParaFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32.0;
    }
}
