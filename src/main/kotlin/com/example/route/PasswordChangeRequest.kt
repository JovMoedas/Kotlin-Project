package com.example.route

data class PasswordChangeRequest(
    val username: String,
    val currentPassword: String,
    val newPassword: String
)
