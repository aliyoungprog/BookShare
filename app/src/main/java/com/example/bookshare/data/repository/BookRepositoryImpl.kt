package com.example.bookshare.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookshare.data.database.FirestoreDb
import com.example.bookshare.domain.BookRepository
import com.example.bookshare.domain.entity.Book
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepositoryImpl: BookRepository {

    // This class should only implement Repository;

    override suspend fun getAllBooks(success:(List<Book>)->Unit){
        val d = FirestoreDb.db.collection("books")
        val books = arrayListOf<Book>()
        d.get().addOnSuccessListener {
            for (x in it){
                if (x != null) {
                    val book: Book = x.toObject(Book::class.java)
                    books.add(book)
                }
            }
            success(books)
        }
    }

    override suspend fun getBookByName(book_name: String) {

    }
}