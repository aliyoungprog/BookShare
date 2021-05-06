package com.example.bookshare.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookshare.R
import com.example.bookshare.data.database.FirestoreDb
import com.example.bookshare.data.database.MyFirebaseAuth
import com.example.bookshare.domain.entity.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity(){

    private lateinit var mUserPassword: TextInputEditText
    private lateinit var mUserEmail: TextInputEditText
    private lateinit var mUserInstagramAccount: TextInputEditText
    private lateinit var mUserTelegramAccount: TextInputEditText
    private lateinit var btnSubmit: Button
    private val firebaseAuth = MyFirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }

    override fun onStart() {
        super.onStart()
        setVars()
        register()
    }

    private fun register(){
        btnSubmit.setOnClickListener{
            auth()
        }
    }

    private fun setVars(){
        mUserInstagramAccount = findViewById(R.id.user_instagram)
        mUserTelegramAccount = findViewById(R.id.user_telegram)
        mUserPassword = findViewById(R.id.user_password)
        mUserEmail = findViewById(R.id.user_mail)
        btnSubmit = findViewById(R.id.btn_submit)
    }

    private fun auth(){
        firebaseAuth.db_auth.createUserWithEmailAndPassword(mUserEmail.text.toString(), mUserPassword.text.toString()).addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()
                val email = mUserEmail.text.toString().toLowerCase()
                val user = User(email = email, instagram_account = mUserInstagramAccount.text.toString(), telegram_account = mUserTelegramAccount.text.toString())
                Firebase.firestore.collection("users").document(email).set(user)
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(this, "Error, ${it.exception}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}