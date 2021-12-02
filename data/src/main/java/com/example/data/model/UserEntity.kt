package com.example.data.model

import com.example.domain.model.Users
import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("id")
    val id:Int,
    @SerializedName("username")
    val userName:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("email")
    val email:String
    ){

    fun mapOf(userEntity: UserEntity): Users {
        return Users(id = userEntity.id,userName = userEntity.userName,mail = userEntity.email)
    }
}
