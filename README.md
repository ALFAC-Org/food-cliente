# Microsserviço `food-cliente` - ALFAC  · ![Coverage](.github/badges/jacoco.svg) ![Branches coverage](.github/badges/branches.svg)

Este repositório fornece o microsserviço de gerenciamento dos clientes da Aplicação Fast Food - ALFAC (veja mais em [https://github.com/ALFAC-Org/food](https://github.com/ALFAC-Org/food)).

## Tecnologia

* Linguagem de Programação: Java 17
* Framework: Spring Boot
* Gerenciador de dependências: Maven
* Banco de dados: MySQL 8
* Documentação e uso de API's: Swagger
* Conteinerização: Docker
* Testes unitários: JUnit 5 + Jacoco
* Testes de comportamento (BDD): Cucumber

## Arquitetura

A aplicação está estruturada no padrão de Clean Architecture. 

Pode ser executada tanto via Docker e Terraform. Podendo ser hospedada tanto localmente ou na nuvem, usando serviços como AWS. 

A interação da aplicação se dá através de APIs com o Swagger disponibilizado.

## Tutoriais

| Passo                                                                                                                                    | Vídeo                                                                                                                                                                                                                                 |
|------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 0. Introdução geral                                                                                                                      | [00-INTRODUCAO_GERAL](https://drive.google.com/file/d/13cn5Z7FZzmkuh7Qo-Y28Z0EYblrOIwRw/view?usp=sharing)                                                                                                                             |
| 1. Introdução aos repositórios                                                                                                           | [01-INTRODUCAO_AOS_REPOSITORIOS](https://drive.google.com/file/d/1YMbt2wP6e9ZTgohPGcBUdwVdxEM2eARD/view?usp=sharing)                                                                                                                  |
| 2. Configuração das variáveis ambiente                                                                                                   | [02-CONFIGURACAO_VARIAVEIS_AMBIENTE](https://drive.google.com/file/d/1pnvF8psjq648Hrk1QU6T_vryq6WkDpC3/view?usp=sharing)                                                                                                              |
| 3. Provisão da infraestrutura com [https://github.com/ALFAC-Org/food-cloud-infra](https://github.com/ALFAC-Org/food-cloud-infra)         | [03-PROVISAO_INFRA_P1](https://drive.google.com/file/d/1RO3DKyr7qyglsGnLnfbETU2rNS2WrM2k/view?usp=sharing) / [03-PROVISAO_INFRA_P2](https://drive.google.com/file/d/1dxIx_XCkQ9s_It_JyRBPpilf5wwh6SEe/view?usp=sharing)               |
| 4. Provisão dos bancos de dados com [https://github.com/ALFAC-Org/food-database](https://github.com/ALFAC-Org/food-database)             | [04-PROVISAO_DB_P1](https://drive.google.com/file/d/1xSe8PQLalyHxy3Pn8XOu3g5v9cOUS4jN/view?usp=sharing) / [04-PROVISAO_DB_P2](https://drive.google.com/file/d/1V_NOCmLwTr8kaZIh56gciOPsf_hsCWUP/view?usp=sharing)                     |
| 5. Provisão das lambdas com [https://github.com/ALFAC-Org/food-serveless-function](https://github.com/ALFAC-Org/food-serveless-function) | [05-PROVISAO_LAMBDAS_P1](https://drive.google.com/file/d/1823lFmz1yaIKIr5s9D8B5prlJcAu4rL0/view?usp=sharing) / [05-PROVISAO_LAMBDAS_P1](https://drive.google.com/file/d/1Nd89oZSWY7JiHjz1RMSN33OU87G0C0pG/view?usp=sharing)           |
| 6. Provisão da aplicação food-produto [https://github.com/ALFAC-Org/food-produto](https://github.com/ALFAC-Org/food-produto)             | [06-PROVISAO_FOOD_PRODUTO_P1](https://drive.google.com/file/d/1_wtyYeHCGJmqkt6kaxhDAm4K21J2apxS/view?usp=sharing) / [06-PROVISAO_FOOD_PRODUTO_P2](https://drive.google.com/file/d/1oFmnhd6c6t9OG7-YzLDs6Ua65qedv3O6/view?usp=sharing) |
| 7. Provisão da aplicação food-cliente [https://github.com/ALFAC-Org/food-cliente](https://github.com/ALFAC-Org/food-cliente)             | [07-PROVISAO_FOOD_CLIENTE_P1](https://drive.google.com/file/d/1x_k4ce2Rexn3E5dnmQJrnhFS6orTdNOK/view?usp=sharing) / [07-PROVISAO_FOOD_CLIENTE_P2](https://drive.google.com/file/d/1WA3WTTX3FD6f-mM6UiV3gLq7IqI7Pik3/view?usp=sharing) |
| 8. Provisão da aplicação principal com [https://github.com/ALFAC-Org/food](https://github.com/ALFAC-Org/food)                            | [08-PROVISIONA_FOOD_P1](https://drive.google.com/file/d/18fwyjMtbWwi4nOUinI-7_72hkPy1YxS8/view?usp=sharing) / [08-PROVISIONA_FOOD_P2](https://drive.google.com/file/d/14Pw2Z9QN9hvKGe40A8mh5OwhgnYl2HwX/view?usp=sharing)             |
| 9. Simulando cliente na plataforma: Load Balancer e API Gateway                                                                          | [09-01_FLUXO_LOAD_BALANCER](https://drive.google.com/file/d/1kUniYudGX6BF6-E_fMayjjyV_pWH1rKr/view?usp=sharing) / [09-02_FLUXO_API_GATEWAY](https://drive.google.com/file/d/1SDrMZ_a94kCGd-VggUDn7TmByYVf3MtU/view?usp=sharing)       |

Todos os vídeos estão disponíveis também aqui: [https://drive.google.com/drive/folders/1C9UbKeiVNRIM7CSv-HMgMHZflwbqI5tQ?usp=sharing](https://drive.google.com/drive/folders/1C9UbKeiVNRIM7CSv-HMgMHZflwbqI5tQ?usp=sharing)

## Executando a aplicação

### GitHub Actions _(recomendada)_

<details>
  <summary>Passo a passo</summary>

> [!WARNING]  
> **Primeiramente, é necessário configurar a infraestrutura por meio do link: [https://github.com/ALFAC-Org/food-cloud-infra/actions/workflows/create-infraestructure.yml](https://github.com/ALFAC-Org/food-cloud-infra/actions/workflows/create-infraestructure.yml)**

1. Acesse [https://github.com/ALFAC-Org/food-cliente/actions](https://github.com/ALFAC-Org/food-cliente/actions) (A guia `Actions` deste repositório);
2. Acesse `CI/CD`;
3. Clique em `Run workflow` (ou Executar workflow);
4. Aguarde. Se tudo der certo, o `check` verde deverá aparecer - o processo dura em torno de 2 minutos;

![applicacao-atualizada-sucesso](docs/aplicacao-atualizada-sucesso.png)

Para acessar a aplicação é necessário acessar a URL da através do Kubernetes, acessando a área de Services e acessando ao serviço `food-cliente`.
A URL será algo como: [http://aa326084c74cf48c6a15f7832f4edb95-21c002b943a9cff6.elb.us-east-1.amazonaws.com:8080/api-docs](http://aa326084c74cf48c6a15f7832f4edb95-21c002b943a9cff6.elb.us-east-1.amazonaws.com:8080/api-docs).

</details>

### Localmente

<details>
  <summary>Passo a passo</summary>

No terminal, execute:

```bash
docker compose up
```

Acesse a aplicação da API em:

[http://localhost:8082/api-docs](http://localhost:8082/api-docs)

</details>

![swagger-aplicacao](docs/swagger-aplicacao.png)

## Testes

Certifique-se de instalar as dependências do projeto antes de executar os testes.

Se seu terminal suportar o comando `make`:

```bash
make build-without-tests
```

senão:

```bash
mvn clean install -U -DskipTests
```

### Unitários

<details>
  <summary>Passo a passo</summary>

No terminal, execute:

Se seu terminal suportar o comando `make`:

```bash
make unit-test-coverage
```

senão:

```bash
mvn clean test -P unit-tests
```

Você poderá ver o relatório de cobertura de testes em `target/site/jacoco/index.html`.

![jacoco-coverage.png](docs/jacoco-coverage.png)

Além disso, é possível ver o coverage atualizado nesta página, ao lado do título do repositório.

</details>

### BDD

<details>
  <summary>Passo a passo</summary>

No terminal, execute:

```bash
docker compose up
```

Em outro terminal, execute:

Se seu terminal suportar o comando `make`:

```bash
make bdd-test
```

senão:

```bash
mvn clean test -P bdd-tests
```

Você poderá ver o relatório de cobertura de testes em `target/cucumber-reports/cucumber.html`.

![cucumber-coverage.png](docs/cucumber-coverage.png)

</details>

## Membros

| Nome | RM | E-mail | GitHub |
| --- | --- | --- | --- |
| Leonardo Fraga | RM354771 | [rm354771@fiap.com.br](mailto:rm354771@fiap.com.br) | [@LeonardoFraga](https://github.com/LeonardoFraga) |
| Carlos Henrique Carvalho de Santana | RM355339 | [rm355339@fiap.com.br](mailto:rm355339@fiap.com.br) | [@carlohcs](https://github.com/carlohcs) |
| Leonardo Alves Campos | RM355568 | [rm355568@fiap.com.br](mailto:rm355568@fiap.com.br) | [@lcalves](https://github.com/lcalves) |
| Andre Musolino | RM355582 | [rm355582@fiap.com.br](mailto:rm355582@fiap.com.br) | [@amusolino](https://github.com/amusolino) |
| Caio Antunes Gonçalves | RM354913 | [rm354913@fiap.com.br](mailto:rm354913@fiap.com.br) | [@caio367](https://github.com/caio367) |
