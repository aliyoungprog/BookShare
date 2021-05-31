package com.aliyoungprog.presentation.fragment.book_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliyoungprog.databinding.ReadBooksFragmentBinding
import com.aliyoungprog.domain.entity.Book
import com.aliyoungprog.presentation.adapters.ItemClickListener
import com.aliyoungprog.presentation.adapters.NewBookAdapter
import com.aliyoungprog.presentation.vm.BooksViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.read_books_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyBooksFragment : Fragment(), ItemClickListener {

    lateinit var bind: ReadBooksFragmentBinding
    lateinit var adapter: NewBookAdapter
    private val bookViewModel by viewModel<BooksViewModel>()
    private lateinit var email: String
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        firebaseAuth = FirebaseAuth.getInstance()
        bind = ReadBooksFragmentBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        email = firebaseAuth.currentUser?.email.toString()
        view.my_books.layoutManager = LinearLayoutManager(context)
        setUpViewModel()
        observeBooks()
    }

    private fun setUpViewModel(){
        bookViewModel.getAllUserBooks(email)
    }

    private fun observeBooks(){
            bind.progressBar.visibility = View.VISIBLE
            bookViewModel.allUserBook.observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer { it ->
                    adapter = NewBookAdapter(it, this)
                    bind.myBooks.adapter = adapter
                    bind.progressBar.visibility = View.GONE
                })
    }

    override fun onItemClicked(book: Book) {

    }
}
