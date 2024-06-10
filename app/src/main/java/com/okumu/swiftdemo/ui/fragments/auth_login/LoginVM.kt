package com.okumu.swiftdemo.ui.fragments.auth_login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sam.swiftsend.data.repository.IAuthRepository
import kotlinx.coroutines.launch


class LoginVM(
    application: Application,
    private val authRepository: IAuthRepository
): AndroidViewModel(application) {

    fun signInWithGoogle(){
        viewModelScope.launch {
            authRepository.signInWithGoogle()
        }
    }
}