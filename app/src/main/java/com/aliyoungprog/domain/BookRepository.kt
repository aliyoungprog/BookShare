package com.aliyoungprog.domain

import android.content.Context
import com.aliyoungprog.domain.entity.Book
import java.util.*

interface BookRepository {
    suspend fun getAllBooks(getAllBooks:(List<Book>)->Unit)
    suspend fun addBookToAll(context: Context?, book: Book, id: UUID)
    suspend fun addBookToUser(book: Book, sender: String)
    suspend fun getAllUserBooks(email: String, getBooks: (List<Book>) -> Unit)
}
