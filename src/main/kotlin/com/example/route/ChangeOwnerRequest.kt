package com.example.route

data class ChangeOwnerRequest(
    val Matricula: String, // Matrícula obrigatória para identificar o carro
    val NovoProprietario: String // Novo proprietário
)