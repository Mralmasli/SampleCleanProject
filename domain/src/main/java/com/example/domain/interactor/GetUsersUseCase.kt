package com.example.domain.interactor

import com.example.domain.model.ResultWrapper
import com.example.domain.model.Users
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend fun execute(): Flow<ResultWrapper<List<Users>>> {
        return userRepository.getUsers()
    }
}