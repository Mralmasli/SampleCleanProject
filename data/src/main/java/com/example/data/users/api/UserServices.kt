package com.example.data.users.api

import com.example.data.model.UserEntity
import retrofit2.Response
import retrofit2.http.GET

interface UserServices {

    @GET("users")
    suspend fun getUsers(): Response<List<UserEntity>>

}