package com.okumu.swiftdemo.ui.fragments.auth_register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.swiftsend.data.repository.AuthRepository
import com.sam.swiftsend.data.repository.IAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterVM(private val authRepository: IAuthRepository
): ViewModel() {

    val username = MutableStateFlow("")
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    private  val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private fun validateFields(): Boolean{
        // 4 characters or more
        if (username.value.length < 4){
            _errorMessage.update { "Your username must be at least 4 characters long" }
            return false
        }

        //valid email
        if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            _errorMessage.update { "You need to add a valid email address"}
            return false
        }

        //password 8 characters or more
        if (password.value.length < 8){
            _errorMessage.update { "Your password is not strong enough, make it at least 8 characters long" }
            return false
        }

        //confirm password
        if (confirmPassword.value != password.value){
            _errorMessage.update { "Your two passwords must match" }
            return false
        }
        _errorMessage.update { "" }
        return true
    }

    fun registerUser(){
        if (validateFields()){
            viewModelScope.launch {
                val authenticatedUser = authRepository.createAccountWithEmailAndPassword(
                    email = email.value,
                    password = password.value
                )
                if (authenticatedUser == null){
                    _errorMessage.update { "Account creation failed, please try later" }
                } else{
                    println("Authenticated User = $authenticatedUser")
                }
            }
        }
    }
}