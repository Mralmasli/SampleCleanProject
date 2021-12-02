package com.example.data.users

import com.example.data.module.NetworkModule
import com.example.data.users.api.UserServices
import com.example.data.users.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class UsersServicesModule {

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserServices {
        return retrofit.create(UserServices::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(userServices: UserServices): UserRepository{
        return UserRepositoryImpl(userServices)
    }
}