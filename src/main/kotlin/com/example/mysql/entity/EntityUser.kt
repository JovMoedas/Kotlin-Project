package com.example.mysql.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object EntityUser:Table<Nothing>(tableName = "user") {
    val userId = int(name = "userId").primaryKey()
    val name = varchar(name = "name")
    val username = varchar(name = "username")
    val password = varchar(name = "password")
    val dob = varchar(name = "dob")
    val gender = varchar(name = "gender")
}