# Conversor de Temperatura — CI com Jenkins + GitHub

Projeto de exemplo para demonstrar um processo de **build e teste automáticos** com Jenkins integrado ao GitHub.

## O projeto

Classe `ConversorTemperatura` (Java 17, Maven) com dois métodos:

| Método | Fórmula |
|---|---|
| `fahrenheitParaCelsius(double f)` | C = (F − 32) × 5 / 9 |
| `celsiusParaFahrenheit(double c)` | F = C × 9 / 5 + 32 |

## Testes (JUnit 5)

7 casos de teste em `ConversorTemperaturaTest`:

- 212 °F → 100 °C, 32 °F → 0 °C, −40 °F → −40 °C
- 0 °C → 32 °F, 100 °C → 212 °F, 37 °C → 98,6 °F
- Ida e volta (C → F → C) devolve o valor original

## Executar localmente

```bash
mvn clean test
```

Relatório de cobertura (JaCoCo): `target/site/jacoco/index.html`

## Pipeline (Jenkinsfile)

| Estágio | O que faz |
|---|---|
| Checkout | Baixa o código do GitHub |
| Build | `mvn clean compile` — erro de compilação ⇒ **FAILURE** |
| Test | `mvn test` + publica resultados JUnit — teste falhando ⇒ **UNSTABLE** |
| Cobertura de código | Só roda com o parâmetro `COBERTURA` marcado (plugin Coverage + JaCoCo) |

## Estrutura

```
├── Jenkinsfile
├── pom.xml
└── src
    ├── main/java/br/ufam/jenkins/ConversorTemperatura.java
    └── test/java/br/ufam/jenkins/ConversorTemperaturaTest.java
```
