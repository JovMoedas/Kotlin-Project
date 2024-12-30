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


## **Estrutura do Projeto**

A estrutura do projeto está organizada da seguinte forma:

```plaintext
src
└── main
    └── kotlin
        └── com.example
            └── model // Modelos de Domínio
              │── Car
              │── User
            └── mysql  // Conexão com o Banco de Dados
              │── DbConection
             └── plugins
              │── Routing.kt
              │── Serialization.kt
             └── route
              │── ChangeOwnerRequest
              │── DeleteCars
              │── DeleteUserRequest
              │── EditCarRequest
              │── LoginRequest
              │── PasswordChangeRequest
              │── RouteUser.kt
             └── util
              │── Aplication.kt


```

---

## **Instalação**

### **Pré-requisitos**

Antes de iniciar o projeto, você precisará instalar os seguintes recursos:

- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Mysql](https://www.mysql.org/download/)
- [Postman](https://postman.org/install/)

---

### **Configurando o banco de dados**

1. **Instale o MySql** e crie um novo banco de dados chamado `ktor`.

2. **Configure a conexão com o banco de dados** no arquivo `DbConection.kt`:

   ```kotlin
   package com.example.mysql
   
   import org.ktorm.database.Database
   object DbConnection {
    private val db: Database? = null
    fun getDatabaseInstance(): Database {
        return db ?: Database.connect(
            url = "jdbc:mysql://127.0.0.1:3306/ktor",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "duarte1234"
        )
    }
   }


### Executando a API

Para rodar a API localmente, siga os passos abaixo:

1. **Navegue até o diretório do projeto:
     ```bash
     git clone https://github.com/JovMoedas/

2. **Execute o projeto com Gradle:**:
    ```bash
     ./gradlew run

3. **A API estará disponível em:**:
     ```bash
     http://localhost:8080

Para testar a API, você pode usar o **Postman**.

### Endpoints Disponíveis

| **Método** | **Endpoint**       | **Descrição**                    | **Exemplo de Entrada**                                                                 |
|------------|--------------------|----------------------------------|--------------------------------------------------------------------------------------|
| **POST**   | `/user/createUser`  | Cria um novo usuário.            | `{ "name": "João", "email": "joao@email.com", "password": "123456" }`                 |
| **GET**    | `/user/getUser/{email}`      | Busca detalhes de um usuário.    | `email = teste@email.com`                                                                             |
| **DELETE**    | `/user/delete`  | Editar um usuário.            | `{  "email": "joao@email.com" }`                 |


| **Método** | **Endpoint**       | **Descrição**                    | **Exemplo de Entrada**                                                                 |
|------------|--------------------|----------------------------------|--------------------------------------------------------------------------------------|
| **POST**   | `/car/createCar`  | Cria um novo carro.            | `{"marca": "Toyota", "modelo": "gtr", "matricula": "00000000" }`                 |
| **GET**    | `/car/getCar/{matricula}`      | Busca detalhes de um carro.    | `matricula = 012938032`                                                                             |
| **DELETE**    | `/car/delete`  | Editar um carro.            | `{  "matricula": "234154" }`                 |

---
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```

