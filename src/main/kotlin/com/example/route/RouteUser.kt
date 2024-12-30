package com.example.route

import com.example.model.Car
import com.example.model.User
import com.example.mysql.DbConnection
import com.example.mysql.entity.EntityCar
import com.example.mysql.entity.EntityUser
import com.example.util.GenericResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.selects.select
import org.jetbrains.exposed.sql.statements.DeleteStatement.Companion.where
import org.jetbrains.exposed.sql.transactions.transaction
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Application.routeUser() {
    val db: Database = DbConnection.getDatabaseInstance()
    routing {
        get("/")
        {
            call.respondText("Welcome to Ktor Mysql")
        }

        get("/users") {
            // Obtém todos os registros da tabela de usuários
            val users = db.from(EntityUser).select().map { row ->
                User(
                    name = row[EntityUser.name],
                    username = row[EntityUser.username],
                    password = row[EntityUser.password],
                    dob = row[EntityUser.dob],
                    gender = row[EntityUser.gender]
                )
            }

            // Verifica se há registros
            if (users.isNotEmpty()) {
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = users)
                )
            } else {
                call.respond(
                    HttpStatusCode.NotFound,
                    GenericResponse(isSuccess = false, data = "No users found")
                )
            }
        }

        post("/login") {
            val loginRequest: LoginRequest = call.receive()

            // Busca o usuário no banco de dados com base no username
            val userFromDb = db.from(EntityUser)
                .select()
                .where { EntityUser.username eq loginRequest.username }
                .map { row ->
                    User(
                        name = row[EntityUser.name] ?: "",
                        username = row[EntityUser.username] ?: "",
                        password = row[EntityUser.password] ?: "",
                        dob = row[EntityUser.dob],
                        gender = row[EntityUser.gender]
                    )
                }
                .singleOrNull()

            if (userFromDb != null && userFromDb.password == loginRequest.password) {
                // Se o usuário for encontrado e a senha for correta
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = "Login successful")
                )
            } else {
                // Se o usuário não for encontrado ou a senha for incorreta
                call.respond(
                    HttpStatusCode.Unauthorized,
                    GenericResponse(isSuccess = false, data = "Invalid username or password")
                )
            }
        }

        delete("/delete-user") {
            val deleteRequest: DeleteUserRequest = call.receive()

            // Busca o usuário no banco de dados com base no username
            val userFromDb = db.from(EntityUser)
                .select()
                .where { EntityUser.username eq deleteRequest.username }
                .map { row ->
                    User(
                        name = row[EntityUser.name] ?: "",
                        username = row[EntityUser.username] ?: "",
                        password = row[EntityUser.password] ?: "",
                        dob = row[EntityUser.dob],
                        gender = row[EntityUser.gender]
                    )
                }
                .singleOrNull()

            if (userFromDb != null && userFromDb.password == deleteRequest.password) {
                // Exclui o usuário do banco de dados
                val noOfRowsAffected = db.delete(EntityUser) {
                    EntityUser.username eq deleteRequest.username

                }

                if (noOfRowsAffected > 0) {
                    call.respond(
                        HttpStatusCode.OK,
                        GenericResponse(isSuccess = true, data = "User deleted successfully")
                    )
                } else {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        GenericResponse(isSuccess = false, data = "Error deleting user")
                    )
                }
            } else {
                call.respond(
                    HttpStatusCode.Unauthorized,
                    GenericResponse(isSuccess = false, data = "Invalid username or password")
                )
            }
        }




        post("/change-password") {
            val passwordChangeRequest: PasswordChangeRequest = call.receive()

            // Busca o usuário no banco de dados com base no username
            val userFromDb = db.from(EntityUser)
                .select()
                .where { EntityUser.username eq passwordChangeRequest.username }
                .map { row ->
                    User(
                        name = row[EntityUser.name] ?: "",
                        username = row[EntityUser.username] ?: "",
                        password = row[EntityUser.password] ?: "",
                        dob = row[EntityUser.dob],
                        gender = row[EntityUser.gender]
                    )
                }
                .singleOrNull()

            if (userFromDb != null && userFromDb.password == passwordChangeRequest.currentPassword) {
                // Atualiza a senha do usuário no banco de dados
                val noOfRowsAffected = db.update(EntityUser) {
                    where { EntityUser.username eq passwordChangeRequest.username }
                    set(EntityUser.password, passwordChangeRequest.newPassword)
                }

                if (noOfRowsAffected > 0) {
                    call.respond(
                        HttpStatusCode.OK,
                        GenericResponse(isSuccess = true, data = "Password updated successfully")
                    )
                } else {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        GenericResponse(isSuccess = false, data = "Error updating password")
                    )
                }
            } else {
                call.respond(
                    HttpStatusCode.Unauthorized,
                    GenericResponse(isSuccess = false, data = "Invalid current password or username")
                )
            }
        }





        post("/register")
        {
            val user: User = call.receive()
            val noOfRowsAffected = db.insert(EntityUser)
            {
                set(it.name, user.name)
                set(it.username, user.username)
                set(it.password, user.password)
                set(it.dob, user.dob)
                set(it.gender, user.gender)
            }

            if (noOfRowsAffected > 0) {
                //success
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = "$noOfRowsAffected rows are affected")
                )
            } else {
                //fail
                call.respond(
                    HttpStatusCode.BadRequest,
                    GenericResponse(isSuccess = true, data = "Error to register the user")
                )
            }
        }



        post("/car-create")
        {
            val car: Car = call.receive()
            val noOfRowsAffected = db.insert(EntityCar)
            {
                set(it.Marca, car.Marca)
                set(it.Modelo, car.Modelo)
                set(it.Matricula, car.Matricula)
                set(it.Ano, car.Ano)
                set(it.Preco, car.Preco)
                set(it.Proprietario, "Stand")
            }

            if (noOfRowsAffected > 0) {
                //success
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = "Car created successfully")
                )
            } else {
                //fail
                call.respond(
                    HttpStatusCode.BadRequest,
                    GenericResponse(isSuccess = true, data = "Error to CREATE the CAR")
                )
            }
        }


        get("/car-list") {
            // Obtém todos os registros da tabela de usuários
            val cars = db.from(EntityCar).select().map { row ->
                Car(
                    Marca = row[EntityCar.Marca],
                    Modelo = row[EntityCar.Modelo],
                    Ano = row[EntityCar.Ano],
                    Matricula = row[EntityCar.Matricula],
                    Preco = row[EntityCar.Preco] ,
                    Proprietario = row[EntityCar.Proprietario]
                )
            }

            // Verifica se há registros
            if (cars.isNotEmpty()) {
                call.respond(
                    HttpStatusCode.OK,
                    GenericResponse(isSuccess = true, data = cars)
                )
            } else {
                call.respond(
                    HttpStatusCode.NotFound,
                    GenericResponse(isSuccess = false, data = "No users found")
                )
            }
        }

        delete("/delete-cars") {
            val deleteRequest: DeleteCars = call.receive()

            // Busca o usuário no banco de dados com base no username
            val carFromDb = db.from(EntityCar)
                .select()
                .where { EntityCar.Matricula eq deleteRequest.Matricula }
                .map { row ->
                    Car(
                        Marca = row[EntityCar.Marca] ?: "",
                        Modelo = row[EntityCar.Modelo] ?: "",
                        Ano = row[EntityCar.Ano] ?: 0,
                        Preco = row[EntityCar.Preco] ?: 0,
                        Matricula = row[EntityCar.Matricula] ?: "",
                        Proprietario = row[EntityCar.Proprietario] ?: "",
                    )
                }
                .singleOrNull()

            if (carFromDb != null && carFromDb.Matricula == deleteRequest.Matricula) {
                // Exclui o usuário do banco de dados
                val noOfRowsAffected = db.delete(EntityCar) {
                    EntityCar.Marca eq deleteRequest.Marca

                }

                if (noOfRowsAffected > 0) {
                    call.respond(
                        HttpStatusCode.OK,
                        GenericResponse(isSuccess = true, data = "car deleted successfully")
                    )
                } else {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        GenericResponse(isSuccess = false, data = "Error deleting car")
                    )
                }
            } else {
                call.respond(
                    HttpStatusCode.Unauthorized,
                    GenericResponse(isSuccess = false, data = "Car not found")
                )
            }
        }

        put("/car-edit") {
            val editRequest: EditCarRequest = call.receive()

            val carFromDb = db.from(EntityCar)
                .select()
                .where { EntityCar.Matricula eq editRequest.Matricula }
                .map { row ->
                    Car(
                        Marca = row[EntityCar.Marca],
                        Modelo = row[EntityCar.Modelo],
                        Ano = row[EntityCar.Ano],
                        Matricula = row[EntityCar.Matricula],
                        Preco = row[EntityCar.Preco] ?: 0, // Preço como inteiro
                        Proprietario = row[EntityCar.Proprietario]
                    )
                }
                .singleOrNull()

            if (carFromDb != null) {
                val noOfRowsAffected = db.update(EntityCar) {
                    where { EntityCar.Matricula eq editRequest.Matricula }
                    set(EntityCar.Marca, editRequest.Marca ?: carFromDb.Marca)
                    set(EntityCar.Modelo, editRequest.Modelo ?: carFromDb.Modelo)
                    set(EntityCar.Ano, editRequest.Ano ?: carFromDb.Ano)
                    set(EntityCar.Preco, editRequest.Preco ?: carFromDb.Preco) // Preço como inteiro
                    set(EntityCar.Proprietario, editRequest.Proprietario ?: carFromDb.Proprietario)
                }

                if (noOfRowsAffected > 0) {
                    call.respond(
                        HttpStatusCode.OK,
                        GenericResponse(isSuccess = true, data = "Car details updated successfully")
                    )
                } else {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        GenericResponse(isSuccess = false, data = "Failed to update car details")
                    )
                }
            } else {
                call.respond(
                    HttpStatusCode.NotFound,
                    GenericResponse(isSuccess = false, data = "Car not found")
                )
            }
        }

        put("/car-change-owner") {
            val changeOwnerRequest: ChangeOwnerRequest = call.receive()

            // Busca o carro no banco de dados com base na matrícula
            val carFromDb = db.from(EntityCar)
                .select()
                .where { EntityCar.Matricula eq changeOwnerRequest.Matricula }
                .map { row ->
                    Car(
                        Marca = row[EntityCar.Marca],
                        Modelo = row[EntityCar.Modelo],
                        Ano = row[EntityCar.Ano],
                        Matricula = row[EntityCar.Matricula],
                        Preco = row[EntityCar.Preco] ?: 0,
                        Proprietario = row[EntityCar.Proprietario]
                    )
                }
                .singleOrNull()

            if (carFromDb != null) {
                // Atualiza o proprietário do carro
                val noOfRowsAffected = db.update(EntityCar) {
                    where { EntityCar.Matricula eq changeOwnerRequest.Matricula }
                    set(EntityCar.Proprietario, changeOwnerRequest.NovoProprietario)
                }

                if (noOfRowsAffected > 0) {
                    call.respond(
                        HttpStatusCode.OK,
                        GenericResponse(isSuccess = true, data = "Car owner changed successfully")
                    )
                } else {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        GenericResponse(isSuccess = false, data = "Failed to change car owner")
                    )
                }
            } else {
                call.respond(
                    HttpStatusCode.NotFound,
                    GenericResponse(isSuccess = false, data = "Car not found")
                )
            }
        }



    }
}