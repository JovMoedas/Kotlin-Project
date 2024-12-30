# Kotlin - Ktor - MySql - API

Bem-vindo à **API**, uma aplicação demonstrativa desenvolvida com Kotlin e Ktor  conectada a um banco de dados MySql. O objetivo deste projeto é fornecer endpoints para gerenciamento de usuários e carros, incluindo registro, autenticação e manipulação de dados básicos(troca de senhas/matriculas).

![Platform](https://img.shields.io/badge/platform-Kotlin-blue.svg)
![Framework](https://img.shields.io/badge/framework-Ktor-blue.svg)  
![Database](https://img.shields.io/badge/database-MySql-blue.svg)  

---

## **Índice**

- [Visão geral](#visão-geral)
- [Recursos](#recursos)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Instalação](#instalação)
  - [Pré-requisitos](#pré-requisitos)
  - [Configurando o banco de dados](#configurando-o-banco-de-dados)
  - [Executando a API](#executando-a-api)
  - [Endpoints Disponíveis](#endpoints-disponíveis)
  - [Exemplos com curl](#exemplos-com-curl)
- [Testando a API](#testing-the-api)

---

## **Visão Geral**

Esta API é projetada para manipular operações básicas de gerenciamento de usuários/carros, oferecendo endpoints RESTful simples e uma estrutura organizada em **Kotlin**.

---

## **Recursos**

- **Registro de Usuários**: Criação de novos usuários com nome, e-mail , senha , genero.
- **Registro de Carros**: Criação de novos carros com nome, marca , modelo , matricula, preço e ano.
- **Edição, exclusão, consulta de Usuarios**: Consulta, exclusão, edição dos usuarios e dos carros.
- **Fácil Configuração**: Pode ser adaptada rapidamente para diferentes bancos de dados.

---


## Features

Here's a list of features included in this project:

| Name                                                     | Description                                                    |
| ----------------------------------------------------------|---------------------------------------------------------------- |
| [Routing](https://start.ktor.io/p/routing)               | Provides a structured routing DSL                              |
| [Authentication](https://start.ktor.io/p/auth)           | Provides extension point for handling the Authorization header |
| [Static Content](https://start.ktor.io/p/static-content) | Serves static files from defined locations                     |

## Building & Running

To build or run the project, use one of the following tasks:

| Task                          | Description                                                          |
| -------------------------------|---------------------------------------------------------------------- |
| `./gradlew test`              | Run the tests                                                        |
| `./gradlew build`             | Build everything                                                     |
| `buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `buildImage`                  | Build the docker image to use with the fat JAR                       |
| `publishImageToLocalRegistry` | Publish the docker image locally                                     |
| `run`                         | Run the server                                                       |
| `runDocker`                   | Run using the local docker image                                     |

If the server starts successfully, you'll see the following output:

```
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```

