package com.example.data.users.repository

import com.example.data.users.api.UserServices
import com.example.domain.model.ResultWrapper
import com.example.domain.model.Users
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userServices: UserServices): UserRepository {
    override suspend fun getUsers(): Flow<ResultWrapper<List<Users>>> {
        return flow {
            val response = userServices.getUsers()
            if(response.isSuccessful){
                val body = response.body()?.map { it.mapOf(it) }
                emit(ResultWrapper.Success(body?:  listOf()))
            }else{
                val errorBody = response.code()
                error(ResultWrapper.Error(errorBody))
            }
        }
    }

}