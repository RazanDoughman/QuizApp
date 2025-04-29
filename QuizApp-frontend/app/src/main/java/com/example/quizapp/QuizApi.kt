package com.example.quizapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface QuizApi {

    @GET("quizzes/category/{category_name}")
    fun getQuizzesByCategory(@Path("category_name") categoryName: String): Call<List<QuizCreate>>

    @POST("create-quiz")
    fun createQuiz(@Body quiz: QuizCreate): Call<QuizCreate>

    @GET("profile/{email}")
    fun getProfile(@Path("email") email: String): Call<UserProfile>

    @POST("profile")
    fun createOrUpdateProfile(@Body user: UserCreate): Call<UserProfile>
}
