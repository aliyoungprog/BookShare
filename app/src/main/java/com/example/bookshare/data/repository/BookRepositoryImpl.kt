package com.example.bookshare.data.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.bookshare.data.database.FirestoreDb
import com.example.bookshare.data.database.MyFirebaseAuth
import com.example.bookshare.domain.BookRepository
import com.example.bookshare.domain.entity.Book
import com.example.bookshare.domain.entity.User
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class BookRepositoryImpl: BookRepository {

    // This class should only implement Repository;
    override suspend fun getAllBooks(success:(List<Book>)->Unit){
        val d = FirestoreDb.db_firestore.collection("books")
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

    override suspend fun addBookToAll(context: Context?, book: Book, id: UUID) {
        FirestoreDb.db_firestore.collection("books").document(id.toString())
            .set(book).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error ${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }


    override suspend fun addBookToUser(book: Book, sender: String) {
            FirestoreDb.db_firestore.collection("users").document(sender)
                .update("myBooks", FieldValue.arrayUnion(book))
    }

    override suspend fun getAllUserBooks(email: String, getBooks: (List<Book>) -> Unit) {
        FirestoreDb.db_firestore.collection("users").document(email).get().addOnSuccessListener{
            val list = it.toObject(User::class.java)?.myBooks
            if (list != null) {
                getBooks(list)
            }
        }
    }
}
