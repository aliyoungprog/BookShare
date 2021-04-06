package com.example.bookshare.presentation.activity

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.example.bookshare.R
import com.example.bookshare.data.database.MyFirebaseAuth
import com.example.bookshare.domain.di.koinModules
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.context.startKoin

class LoginActivity : AppCompatActivity() {

    private lateinit var mUserPassword: TextInputEditText
    private lateinit var mUserEmail: TextInputEditText
    private lateinit var mCreateAccount: AppCompatTextView
    private lateinit var btnLogin: Button
    private val firebaseAuth = MyFirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    override fun onStart() {
        super.onStart()
        setVars()
        checkFirebase()
        createAccountPressed()
        login()
    }

    private fun createAccountPressed(){
        mCreateAccount.setOnClickListener{
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    private fun login(){
        btnLogin.setOnClickListener {
            firebaseAuth.db_auth.signInWithEmailAndPassword(
                mUserEmail.text.toString(),
                mUserPassword.text.toString()
            ).addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                } else {
                    Toast.makeText(this, "Error, ${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkFirebase(){
        if (firebaseAuth.db_auth.currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    private fun setVars(){
        mUserEmail = findViewById(R.id.login_user_mail)
        mUserPassword = findViewById(R.id.login_user_password)
        mCreateAccount = findViewById(R.id.create_account)
        btnLogin = findViewById(R.id.btn_submit)
    }

}