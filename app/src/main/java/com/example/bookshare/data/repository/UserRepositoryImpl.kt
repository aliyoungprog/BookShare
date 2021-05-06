package com.example.bookshare.data.repository

import android.util.Log
import com.example.bookshare.data.database.FirestoreDb
import com.example.bookshare.domain.UserRepository
import com.example.bookshare.domain.entity.User


class UserRepositoryImpl : UserRepository{
    override suspend fun getUserByEmail(email: String, setUser: (user: User?) -> Unit){
        FirestoreDb.db_firestore.collection("users").document(email).get().addOnSuccessListener {
            val user = it.toObject(User::class.java)
            Log.d("user", "setViewModel: ${user}")
            setUser(user)
        }
    }
}