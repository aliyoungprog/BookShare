package com.example.bookshare.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bookshare.data.database.FirestoreDb
import com.example.bookshare.databinding.BookAddFragmentBinding
import com.example.bookshare.domain.entity.Book
import com.example.bookshare.presentation.vm.BooksViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddBookMainFragment : Fragment() {

    private lateinit var bind: BookAddFragmentBinding
    private lateinit var bookName: TextInputEditText
    private lateinit var bookAuthor: TextInputEditText
    private lateinit var addBookBtn: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var email: String

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
    }

    private fun setUpUi(){
        bookName = bind.bookName
        bookAuthor = bind.bookAuthor
        addBookBtn = bind.addBook
        email = firebaseAuth.currentUser?.email.toString()
        addBookBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                //Log.d("coroutines", "coroutine scope")
                val book = Book(name = bookName.text.toString(), author = bookAuthor.text.toString(), sender = email)
                bind.addBookProgress.visibility = View.VISIBLE
                addBookToAll(context = context, book = book, sender = email)
                addBookToUser(book)
                bind.addBookProgress.visibility = View.GONE
            }
        }
    }

    private fun addBookToAll(context: Context?, book: Book, sender: String){
        bookViewModel.insertBookToAll(context, book, sender)
    }

    private fun addBookToUser(book: Book){
        bookViewModel.insertBookToUser(book)
    }
}