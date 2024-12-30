package com.example.model

import com.google.gson.annotations.SerializedName

data class Car(

	@field:SerializedName("Marca")
	val Marca: String? = null,
	@field:SerializedName("Modelo")
	val Modelo: String? = null,
	@field:SerializedName("Ano")
	val Ano: Int? = null,
	@field:SerializedName("Preco")
	val Preco: Int? = null,
	@field:SerializedName("Matricula")
	val Matricula: String? = null,
	@field:SerializedName("Proprietario")
	val Proprietario: String? = null
)
