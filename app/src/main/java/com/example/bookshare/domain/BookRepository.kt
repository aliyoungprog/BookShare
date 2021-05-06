package com.example.bookshare.domain

import android.content.Context
import android.content.IntentSender
import android.util.Log
import com.example.bookshare.data.database.FirestoreDb
import com.example.bookshare.domain.entity.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

interface BookRepository {
    suspend fun getAllBooks(getAllBooks:(List<Book>)->Unit)
    suspend fun addBookToAll(context: Context?, book: Book, id: UUID)
    suspend fun addBookToUser(book: Book, sender: String)
    suspend fun getAllUserBooks(email: String, getBooks: (List<Book>) -> Unit)
}
