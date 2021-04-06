package com.example.bookshare.presentation.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshare.data.repository.UserRepositoryImpl
import com.example.bookshare.domain.entity.User
import kotlinx.coroutines.launch
import java.lang.Exception


class UserViewModel(val userRepositoryImpl: UserRepositoryImpl) : ViewModel(){

    val userLiveData = MutableLiveData<User>()

    fun getUserByEmail(email: String){
            try {
                viewModelScope.launch {
                    userRepositoryImpl.getUserByEmail(email) {
                        userLiveData.value = it
                    }
                }
            }catch (e: Exception){

            }
    }
}