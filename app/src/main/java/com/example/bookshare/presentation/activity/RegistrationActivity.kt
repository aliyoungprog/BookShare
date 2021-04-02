package com.example.bookshare.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookshare.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity(){

    private lateinit var mUserPassword: TextInputEditText
    lateinit var mUserEmail: TextInputEditText
    private lateinit var btnSubmit: Button
    private lateinit var firebaseAuth: FirebaseAuth

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
            firebaseAuth.createUserWithEmailAndPassword(mUserEmail.text.toString(), mUserPassword.text.toString()).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    Toast.makeText(this, "Error, ${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setVars(){
        mUserPassword = findViewById(R.id.user_password)
        mUserEmail = findViewById(R.id.user_mail)
        btnSubmit = findViewById(R.id.btn_submit)
        firebaseAuth = FirebaseAuth.getInstance()
    }
}