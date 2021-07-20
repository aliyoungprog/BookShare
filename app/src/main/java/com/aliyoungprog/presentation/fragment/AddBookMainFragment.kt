package com.aliyoungprog.presentation.fragment

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.aliyoungprog.data.database.MyFirebaseStorage
import com.aliyoungprog.databinding.BookAddFragmentBinding
import com.aliyoungprog.domain.entity.Book
import com.aliyoungprog.presentation.vm.BooksViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.book_add_fragment.*
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.util.*

class AddBookMainFragment : Fragment() {

    private lateinit var bind: BookAddFragmentBinding
    private lateinit var bookName: TextInputEditText
    private lateinit var bookAuthor: TextInputEditText
    private lateinit var bookDescription: TextInputEditText
    private lateinit var addBookBtn: Button
    private lateinit var addImgBtn: ImageView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var email: String
    private lateinit var bookImg: ImageView
    private lateinit var getContent: ActivityResultLauncher<String>

    private val bookViewModel by viewModel<BooksViewModel>()

    companion object {
        fun getInstance() = AddBookMainFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        firebaseAuth = FirebaseAuth.getInstance()
        bind = BookAddFragmentBinding.inflate(inflater)
        return bind.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            bookImg.setImageURI(uri)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        bindBookToUser()
    }

    private fun setUpUi(){
        bookName = bind.bookName
        bookAuthor = bind.bookAuthor
        bookDescription = bind.bookDescription
        addBookBtn = bind.addBook
        addImgBtn = bind.addImg
        bookImg = bind.bookImageView
        addImgBtn.setOnClickListener{
            getImageFromGallery()
        }
    }

    private fun checkFields(): Boolean{
        if (bookName.text.isNullOrEmpty()) {
            Toast.makeText(context, "Пожалуйста, добавьте название книги :)", Toast.LENGTH_SHORT).show()
            return false
        }
        if (bookAuthor.text.isNullOrEmpty()){
            Toast.makeText(context, "Пожалуйста, добавьте автора книги :)", Toast.LENGTH_SHORT).show()
            return false
        }
        if (bookDescription.text.isNullOrEmpty()){
            Toast.makeText(context, "Пожалуйста, добавьте описание книги :)", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


    private fun bindBookToUser(){
        addBookBtn.setOnClickListener {
            if (checkFields()) {
                CoroutineScope(Dispatchers.Main).launch {
                    //Log.d("coroutines", "coroutine scope")
                    email = firebaseAuth.currentUser?.email.toString().toLowerCase()
                    val book = Book(
                        name = bookName.text.toString(),
                        author = bookAuthor.text.toString(),
                        description = bookDescription.text.toString(),
                        sender = email,
                        //book_img = bits.toString()
                    )
                    bind.addBookProgress.visibility = View.VISIBLE
                    addBookToAll(context = context, book = book, id = UUID.randomUUID())
                    addBookToUser(book)
                    uploadToStorage(book_name.text.toString())
                    bind.addBookProgress.visibility = View.GONE
                }
            }
        }
    }

    private fun addBookToAll(context: Context?, book: Book, id: UUID){
        bookViewModel.insertBookToAll(context, book, id)
    }

    private fun addBookToUser(book: Book){
        bookViewModel.insertBookToUser(book)
    }

    private fun getImageFromGallery(){
        getContent.launch("image/*")
    }

    private fun uploadToStorage(book_name: String){
        val bitmap = (bookImg.drawable as BitmapDrawable).bitmap
        val byteArrayStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayStream)
        val data = byteArrayStream.toByteArray()
        CoroutineScope(Dispatchers.IO).launch {
            var uploadTask = MyFirebaseStorage.db_storage.getReference(book_name).putBytes(data)
            uploadTask.addOnFailureListener {
                Toast.makeText(context, "Что-то пошло не так :(", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener { taskSnapshot ->
                Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
