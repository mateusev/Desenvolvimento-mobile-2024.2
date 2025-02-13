package com.example.auth1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth1.data.AuthRepository
import kotlinx.coroutines.launch


class AuthViewModel(
    private val repository: AuthRepository
): ViewModel() {

    var loginResult : ((Boolean) -> Unit)? = null
    var registerResult : ((Boolean) -> Unit)? = null

    fun login (
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            val success = repository.loginUser(email, password)
            onResult(success)
        }
    }

    fun resetPassword(
        email: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val success = repository.resetPassword(email)
            onResult(success)
        }
    }



}