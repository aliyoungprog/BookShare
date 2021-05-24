package com.example.bookshare.presentation.fragment

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bookshare.data.database.FirestoreDb
import com.example.bookshare.data.database.MyFirebaseStorage
import com.example.bookshare.databinding.BookAddFragmentBinding
import com.example.bookshare.domain.entity.Book
import com.example.bookshare.presentation.vm.BooksViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import kotlinx.android.synthetic.main.book_add_fragment.*
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
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
    private var bits: Bitmap? = null

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
            getImageFromGalery()
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

    private fun getImageFromGalery(){
        val i = Intent(Intent.ACTION_PICK)
        i.type = "image/*"
        startActivityForResult(i, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100){
            val uri = data?.data
            bookImg.setImageURI(uri)
        }
    }
    private fun getExtension(uri: Uri): String?{
        val cr = context?.contentResolver
        val m = MimeTypeMap.getSingleton()
        return m.getExtensionFromMimeType(cr?.getType(uri))
    }
    private fun uploadToStorage(book_name: String){
        // Get the data from an ImageView as bytes
        bookImg.isDrawingCacheEnabled = true
        bookImg.buildDrawingCache()
        val bitmap = (bookImg.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = MyFirebaseStorage.db_storage.getReference(book_name).putBytes(data)
        uploadTask.addOnFailureListener {
            Toast.makeText(context, "Что-то пошло не так :(", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener { taskSnapshot ->
            Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
        }
    }
}
