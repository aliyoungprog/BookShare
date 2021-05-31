package com.aliyoungprog.data.repository

import android.util.Log
import com.aliyoungprog.data.database.FirestoreDb
import com.aliyoungprog.domain.UserRepository
import com.aliyoungprog.domain.entity.User


class UserRepositoryImpl : UserRepository{
    override suspend fun getUserByEmail(email: String, setUser: (user: User?) -> Unit){
        FirestoreDb.db_firestore.collection("users").document(email).get().addOnSuccessListener {
            val user = it.toObject(User::class.java)
            Log.d("user", "setViewModel: ${user}")
            setUser(user)
        }
    }
}
