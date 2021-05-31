package com.aliyoungprog.presentation.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliyoungprog.domain.BookRepository
import com.aliyoungprog.domain.entity.Book
import kotlinx.coroutines.launch
import timber.log.Timber
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
            repository.addBookToAll(context, book, id)
        }
    }

    fun insertBookToUser(book: Book){
        viewModelScope.launch {
            repository.addBookToUser(book, book.sender!!)
        }
    }

    fun getAllUserBooks(email: String){
        viewModelScope.launch {
            repository.getAllUserBooks(email){
                Timber.tag("books amount").i("{$it.size}")
                allUserBook.value = it
            }
        }
    }
}
