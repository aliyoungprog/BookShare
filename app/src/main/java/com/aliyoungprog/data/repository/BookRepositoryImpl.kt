package com.aliyoungprog.data.repository

import android.content.Context
import android.widget.Toast
import com.aliyoungprog.data.database.FirestoreDb
import com.aliyoungprog.domain.BookRepository
import com.aliyoungprog.domain.entity.Book
import com.aliyoungprog.domain.entity.User
import com.google.firebase.firestore.FieldValue
import timber.log.Timber
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
            }else{
                getBooks(listOf())
            }
        }
    }
}
