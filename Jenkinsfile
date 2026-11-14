// Pipeline declarativo: Build -> Teste -> Cobertura (opcional)
//
// Resultado esperado em cada cenario:
//   Cenario 1 (tudo certo)         -> SUCCESS  (bolinha azul/verde)
//   Cenario 2 (erro de compilacao) -> FAILURE  (bolinha vermelha)
//   Cenario 3 (teste falhando)     -> UNSTABLE (bolinha amarela)
//   Cenario 4 (agendado)           -> configurado na interface (Build periodically) ou pela linha 'triggers' abaixo
//   Cenario 5 (cobertura)          -> marcar o parametro COBERTURA ao rodar "Build with Parameters"

// Funciona em Linux/macOS (sh) e Windows (bat)
def executar(String comando) {
    if (isUnix()) {
        sh comando
    } else {
        bat comando
    }
}

pipeline {
    agent any

    parameters {
        booleanParam(
            name: 'COBERTURA',
            defaultValue: false,
            description: 'Cenario 5: publica o relatorio de cobertura de codigo (JaCoCo)'
        )
    }

    // Cenario 4 pela Jenkinsfile (alternativa a interface). Descomente para agendar:
    // triggers { cron('H/2 * * * *') }   // a cada ~2 minutos (bom para gravar o video)

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    stages {
        stage('Checkout') {
            steps {
                // Baixa o codigo do GitHub configurado no job
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Compila os fontes. Erro de compilacao => esta etapa falha => build FAILURE
                executar 'mvn -B -ntp clean compile'
            }
        }

        stage('Test') {
            steps {
                // -Dmaven.test.failure.ignore=true: o Maven NAO falha o build quando um teste falha.
                // Assim o Jenkins le o relatorio e marca o build como UNSTABLE (e nao FAILURE).
                executar 'mvn -B -ntp test -Dmaven.test.failure.ignore=true'
            }
            post {
                always {
                    // Publica os resultados dos testes; se houver falhas, marca o build como UNSTABLE
                    junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: false
                }
            }
        }

        stage('Cobertura de codigo') {
            when {
                expression { params.COBERTURA }
            }
            steps {
                // Requer o plugin "Coverage" do Jenkins (le o jacoco.xml gerado no 'mvn test')
                recordCoverage(
                    tools: [[parser: 'JACOCO', pattern: 'target/site/jacoco/jacoco.xml']],
                    sourceCodeRetention: 'EVERY_BUILD'
                )
            }
        }
    }

    post {
        success  { echo 'Build e testes concluidos com sucesso.' }
        unstable { echo 'Build compilou, mas ha testes falhando (UNSTABLE).' }
        failure  { echo 'Build falhou (veja o log do estagio que ficou vermelho).' }
    }
}
