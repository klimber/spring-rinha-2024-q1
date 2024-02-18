# Rinha de Backend: 2024/Q1 - Crébito

Submissão para a segunda edição da Rinha de Backend feita em Java com Spring Boot.

## Para Executar

Inicialize a database postgres com os scripts SQL da pasta `conf`.

Defina as variáveis de ambiente:
* POSTGRES_USER
* POSTGRES_PASSWORD
* POSTGRES_HOSTNAME
* POSTGRES_DB
* PORT (Optional, default 8080)

Então, execute o seguinte comando:

    ./gradlew bootRun

Ou então use `docker compose up -d` para subir utilizando a configuração do arquivo `docker-compose.yaml`


## Submissão

O diretório [participacao](./participacao) contem os artefatos usados para a submissão da Rinha de Backend.
