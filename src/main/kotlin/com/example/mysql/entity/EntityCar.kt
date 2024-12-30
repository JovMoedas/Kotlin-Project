package com.example.mysql.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object EntityCar:Table<Nothing>(tableName = "car") {
    val carId = int(name = "carId").primaryKey()
    val Marca = varchar(name = "Marca")
    val Modelo = varchar(name = "Modelo")
    val Ano = int(name = "Ano")
    val Matricula = varchar(name = "Matricula")
    val Preco = int(name = "Preco")
    val Proprietario = varchar(name = "Proprietario")
}