package com.example.bookshare.presentation.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshare.data.repository.BookRepositoryImpl
import com.example.bookshare.domain.entity.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BooksViewModel(val repositoryImpl: BookRepositoryImpl): ViewModel() {
    // Can talk only with repository

    val booksLiveData = MutableLiveData<List<Book>>()
    val allUserBook = MutableLiveData<List<Book>>()

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

    fun insertBookToAll(context: Context?, book: Book, sender: String){
        viewModelScope.launch {
            //Log.d("coroutine", "viewModelScope")
            repositoryImpl.addBookToAll(context, book, sender)
        }
    }

    fun insertBookToUser(book: Book){
        viewModelScope.launch {
            //Log.d("coroutine", "viewModelScope1")
            repositoryImpl.addBookToUser(book, book.sender!!)
        }
    }

    fun getAllUserBooks(email: String){
        viewModelScope.launch {
            repositoryImpl.getAllUserBooks(email){
                allUserBook.value = it
            }
        }
    }
}