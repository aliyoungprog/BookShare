package com.example.bookshare.data.database

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirestoreDb {
    @SuppressLint("StaticFieldLeak")
    val db_firestore = Firebase.firestore
}

object MyFirebaseAuth{
    val db_auth = FirebaseAuth.getInstance()
}