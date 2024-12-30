package com.example.route

data class EditCarRequest(
    val Matricula: String, // Matrícula obrigatória para identificar o carro
    val Marca: String? = null, // Campos opcionais
    val Modelo: String? = null,
    val Ano: Int? = null,
    val Preco: Int? = null, // Alterado para Int
    val Proprietario: String? = null
)