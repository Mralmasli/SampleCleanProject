package com.example.domain.repository

import com.example.domain.model.ResultWrapper
import com.example.domain.model.Users
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(): Flow<ResultWrapper<List<Users>>>

}