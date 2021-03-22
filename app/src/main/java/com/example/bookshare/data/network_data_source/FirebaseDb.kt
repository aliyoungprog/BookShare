package com.example.bookshare.data.network_data_source

import android.content.ContentValues.TAG
import android.util.Log
import com.example.bookshare.data.disk_data_source.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDb {

    fun addUser(login: String, password: String){
        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
    }


}
