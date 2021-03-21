package com.example.bookshare.data.network_data_source

import android.content.ContentValues.TAG
import android.util.Log
import com.example.bookshare.data.disk_data_source.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDb {

    private lateinit var database: DatabaseReference

    fun init(){
        database = Firebase.database.reference
    }


    fun addUser(userId: String, login: String, email: String){
        val user = User(login, email)
        database.child("users").child(userId).setValue(user).addOnSuccessListener {
            Log.d(TAG, "addUser: {$it}")
        }
    }


}
