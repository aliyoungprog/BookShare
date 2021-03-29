package com.example.bookshare.domain

import android.util.Log
import com.example.bookshare.data.database.FirestoreDb
import com.example.bookshare.domain.entity.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface BookRepository {
    suspend fun getAllBooks(success:(List<Book>)->Unit)
    suspend fun getBookByName(book_ame: String)
}