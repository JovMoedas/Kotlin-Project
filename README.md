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
  - [Endpoints Disponíveis](#endpoints-details)
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
     http://192.168.56.1:8080

Para testar a API, você pode usar o **Postman**.

##  **Endpoints Overview**

| **Method** | **Endpoint**         | **Description**                |
|------------|-----------------------|--------------------------------|
| `GET`      | `/`                  | Welcome message                |
| `GET`      | `/users`             | Fetch all users                |
| `POST`     | `/login`             | User login                     |
| `DELETE`   | `/delete-user`       | Delete a user                  |
| `POST`     | `/change-password`   | Change user password           |
| `POST`     | `/register`          | Register a new user            |
| `POST`     | `/car-create`        | Create a new car entry         |
| `GET`      | `/car-list`          | Fetch all cars                 |
| `DELETE`   | `/delete-cars`       | Delete a car                   |
| `PUT`      | `/car-edit`          | Edit car details               |
| `PUT`      | `/car-change-owner`  | Change car owner               |

---

##  **Endpoints Details**

###  **User Endpoints**

#### `GET /`
- **Description:** Retorna uma mensagem de boas-vindas.  
- **Response Example:**
    ```json
    {
      "message": "Welcome to Ktor Mysql"
    }
    ```

#### `GET /users`
- **Description:** Busca todos os usuários no banco de dados.  
- **Response Example:**
    ```json
    {
      "isSuccess": true,
      "data": [
        {
          "name": "John Doe",
          "username": "johndoe",
          "password": "1234",
          "dob": "1990-01-01",
          "gender": "Male"
        }
      ]
    }
    ```

#### `POST /login`
- **Description:** Realiza o login do usuário.  
- **Request Example:**
    ```json
    {
      "username": "johndoe",
      "password": "1234"
    }
    ```
- **Response Example:**
    ```json
    {
      "isSuccess": true,
      "data": "Login successful"
    }
    ```

#### `DELETE /delete-user`
- **Description:** Deleta um usuário.  
- **Request Example:**
    ```json
    {
      "username": "johndoe",
      "password": "1234"
    }
    ```
- **Response Example:**
    ```json
    {
      "isSuccess": true,
      "data": "User deleted successfully"
    }
    ```

#### `POST /change-password`
- **Description:** Altera a senha de um usuário.  
- **Request Example:**
    ```json
    {
      "username": "johndoe",
      "currentPassword": "1234",
      "newPassword": "5678"
    }
    ```
- **Response Example:**
    ```json
    {
      "isSuccess": true,
      "data": "Password updated successfully"
    }
    ```

#### `POST /register`
- **Description:** Registra um novo usuário.  
- **Request Example:**
    ```json
    {
      "name": "John Doe",
      "username": "johndoe",
      "password": "1234",
      "dob": "1990-01-01",
      "gender": "Male"
    }
    ```
- **Response Example:**
    ```json
    {
      "isSuccess": true,
      "data": "1 rows are affected"
    }
    ```

---

### **Car Endpoints**

#### `POST /car-create`
- **Description:** Cria uma nova entrada de carro.  
- **Request Example:**
    ```json
    {
      "Marca": "Toyota",
      "Modelo": "Corolla",
      "Matricula": "XYZ1234",
      "Ano": 2020,
      "Preco": 20000
    }
    ```
- **Response Example:**
    ```json
    {
      "isSuccess": true,
      "data": "Car created successfully"
    }
    ```

#### `GET /car-list`
- **Description:** Busca todos os carros no banco de dados.  
- **Response Example:**
    ```json
    {
      "isSuccess": true,
      "data": [
        {
          "Marca": "Toyota",
          "Modelo": "Corolla",
          "Matricula": "XYZ1234",
          "Ano": 2020,
          "Preco": 20000,
          "Proprietario": "Stand"
        }
      ]
    }
    ```

#### `DELETE /delete-cars`
- **Description:** Deleta um carro pela matrícula.  
- **Request Example:**
    ```json
    {
      "Matricula": "XYZ1234",
      "Marca": "Toyota"
    }
    ```
- **Response Example:**
    ```json
    {
      "isSuccess": true,
      "data": "Car deleted successfully"
    }
    ```

#### `PUT /car-edit`
- **Description:** Edita detalhes de um carro.  
- **Request Example:**
    ```json
    {
      "Matricula": "XYZ1234",
      "Marca": "Toyota",
      "Modelo": "Camry",
      "Ano": 2021,
      "Preco": 22000,
      "Proprietario": "John Doe"
    }
    ```
- **Response Example:**
    ```json
    {
      "isSuccess": true,
      "data": "Car details updated successfully"
    }
    ```

#### `PUT /car-change-owner`
- **Description:** Altera o proprietário de um carro.  
- **Request Example:**
    ```json
    {
      "Matricula": "XYZ1234",
      "NovoProprietario": "Jane Doe"
    }
    ```
- **Response Example:**
    ```json
    {
      "isSuccess": true,
      "data": "Car owner changed successfully"
    }
    ```

---

##  **Best Practices**
- Certifique-se de enviar os dados no formato correto.  
- Use cabeçalhos apropriados, como `Content-Type: application/json`.  
- Sempre verifique as respostas para capturar possíveis erros.

---



2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://192.168.56.1:8080
```

