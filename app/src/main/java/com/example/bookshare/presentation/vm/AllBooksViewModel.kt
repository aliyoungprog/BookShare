package com.example.bookshare.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshare.data.repository.BookRepositoryImpl
import com.example.bookshare.domain.entity.Book
import kotlinx.coroutines.launch



class AllBooksViewModel(val repositoryImpl: BookRepositoryImpl): ViewModel() {
    // Can talk only with repository

    val booksLiveData = MutableLiveData<List<Book>>()
    fun getAllBooks(){
        viewModelScope.launch {
            try{
                repositoryImpl.getAllBooks {
                    booksLiveData.value = it
                }
            }catch(e: Exception){
                //
            }
        }
    }
}