package com.example.sampleproject.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.GetUsersUseCase
import com.example.domain.model.ResultWrapper
import com.example.domain.model.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    private val _users = MutableStateFlow(emptyList<Users>())
    val users = _users.asStateFlow()

    private val _error = MutableSharedFlow<String?>()
    val error = _error.asSharedFlow()

    fun getData(){
        viewModelScope.launch {
            getUsersUseCase.execute()
                .onStart {

                }
                .catch { ex ->
                    Log.i("TAG","${ex.message}")
                    _error.emit(ex.message)
                }
                .collect {
                    if(it is ResultWrapper.Success){
                        Log.i("TAG", "${it.data} ")
                        _users.value = it.data
                    }
                }
        }
    }
}