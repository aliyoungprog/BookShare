package com.aliyoungprog.data.database

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

object FirestoreDb {
    @SuppressLint("StaticFieldLeak")
    val db_firestore = Firebase.firestore
}

object MyFirebaseAuth{
    val db_auth = FirebaseAuth.getInstance()
}

object MyFirebaseStorage{
    val db_storage = FirebaseStorage.getInstance()
}
