package com.example.model

import com.google.gson.annotations.SerializedName

data class User(

	@field:SerializedName("username")
	val username: String? = null,
	@field:SerializedName("password")
	val password: String? = null,
	@field:SerializedName("gender")
	val gender: String? = null,
	@field:SerializedName("dob")
	val dob: String? = null,
	@field:SerializedName("name")
	val name: String? = null
)
