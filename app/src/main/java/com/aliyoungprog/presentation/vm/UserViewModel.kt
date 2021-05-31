package com.aliyoungprog.presentation.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliyoungprog.domain.UserRepository
import com.aliyoungprog.domain.entity.User
import kotlinx.coroutines.launch
import java.lang.Exception


class UserViewModel(val userRepository: UserRepository) : ViewModel(){

    val userLiveData = MutableLiveData<User>()
    fun getUserByEmail(email: String){
            try {
                viewModelScope.launch {
                    userRepository.getUserByEmail(email) {
                        userLiveData.value = it
                        Log.d("user", "setViewModel: ${it}")
                    }
                }
            }catch (e: Exception){

            }
    }
}
