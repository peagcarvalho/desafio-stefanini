# Desafio Stefanini - BASA

Desenvolvimento de uma API RESTful com Spring Boot para fazer o CRUD da entidade Processo

## Funcionalidades

- Cadastrar, atualizar, buscar e excluir Processo
- Listar Processos por data, situação (em andamento, julgado ou arquivado) e se é público ou privado
- Gerar relatório de Processos

## Pré-Requisitos

Antes de tentar executar o projeto, você precisa ter o [Java](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html), [Git](https://git-scm.com/) e o [Oracle DB](https://www.oracle.com/br/database/technologies/oracle-database-software-downloads.html#19c) instalados na sua máquina, além de alguma IDE como [Eclipse](https://www.eclipse.org/downloads/packages/release/2020-06/r/eclipse-ide-enterprise-java-developers) ou [Spring Tools](https://spring.io/tools).

## Como Executar

```
# Clone esse repositório
$ git clone https://github.com/peagcarvalho/desafio-stefanini

# Adicione o projeto ao Eclipse ou Spring Tools

# Execute o arquivo dump.sql no seu Banco de Dados Oracle

# Encontre o arquivo application.properties em src/main/resources e adicione o nome de usuário e senha do banco de dados nas propriedades:
spring.datasource.username e spring.datasource.password
```

## Tecnologias

As seguintes ferramentas foram utilizadas para construir o projeto:

* [Spring Boot](https://spring.io/projects/spring-boot) - com Spring Data JPA e Bean Validation
* [Oracle Database](https://www.oracle.com/database/technologies/xe-prior-releases.html) - 11g Express Edition

## Autor

* Desenvolvido por [Pedro Carvalho](https://github.com/peagcarvalho)
