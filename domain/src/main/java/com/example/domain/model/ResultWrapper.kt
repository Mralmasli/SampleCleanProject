package com.example.domain.model

sealed class ResultWrapper <out T> {
    data class Success <T>(val data : T) : ResultWrapper<T>()
    data class Error <U>(val rawResponse: U) : ResultWrapper<U>()
}