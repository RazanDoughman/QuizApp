package com.example.quizapp

data class UserProfile(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val bio: String?,
    val phone_number: String?,
    val email: String,
    val points: Int
)
