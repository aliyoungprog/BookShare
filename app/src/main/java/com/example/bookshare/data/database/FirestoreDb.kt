package com.example.bookshare.data.database

import android.annotation.SuppressLint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirestoreDb {
    @SuppressLint("StaticFieldLeak")
    val db = Firebase.firestore
}