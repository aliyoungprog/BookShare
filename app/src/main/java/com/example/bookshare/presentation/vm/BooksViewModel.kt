package com.example.bookshare.presentation.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshare.data.repository.BookRepositoryImpl
import com.example.bookshare.domain.BookRepository
import com.example.bookshare.domain.entity.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class BooksViewModel(val repository: BookRepository): ViewModel() {
    // Can talk only with repository

    val booksLiveData = MutableLiveData<List<Book>>()
    val allUserBook = MutableLiveData<List<Book>>()

    fun getAllBooks(){
        viewModelScope.launch {
            try{
                repository.getAllBooks {
                    booksLiveData.value = it
                }
            }catch(e: Exception){
                //
            }
        }
    }

    fun insertBookToAll(context: Context?, book: Book, id: UUID){
        viewModelScope.launch {
            //Log.d("coroutine", "viewModelScope")
            repository.addBookToAll(context, book, id)
        }
    }

    fun insertBookToUser(book: Book){
        viewModelScope.launch {
            //Log.d("coroutine", "viewModelScope1")
            repository.addBookToUser(book, book.sender!!)
        }
    }

    fun getAllUserBooks(email: String){
        viewModelScope.launch {
            repository.getAllUserBooks(email){
                allUserBook.value = it
            }
        }
    }
}
