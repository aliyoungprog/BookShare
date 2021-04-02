package com.example.bookshare.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookshare.R
import com.example.bookshare.data.repository.BookRepositoryImpl
import com.example.bookshare.databinding.HomeFragmentBinding
import com.example.bookshare.domain.entity.Book
import com.example.bookshare.presentation.adapters.ItemClickListener
import com.example.bookshare.presentation.adapters.NewBookAdapter
import com.example.bookshare.presentation.vm.AllBooksViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment(), ItemClickListener {

    // This class contain only the instance of ViewModel class;
    // Ideally fragments should be self contained

    lateinit var adapter: NewBookAdapter
    private val bookViewModel by viewModel<AllBooksViewModel>()
    lateinit var binding: HomeFragmentBinding

    companion object{
        fun getInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        view.new_books.layoutManager = LinearLayoutManager(context)
        setUpViewModel()
        observeBooks()
    }


    private fun observeBooks(){
        binding.progressBar.visibility = View.VISIBLE
        bookViewModel.booksLiveData.observe(viewLifecycleOwner, Observer { it ->
            adapter = NewBookAdapter(it, this)
            binding.newBooks.adapter = adapter
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun setUpViewModel(){
        binding.viewModel = bookViewModel
        bookViewModel.getAllBooks()
    }

    override fun onItemClicked(book: Book) {
        setUpBundle(book)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, BookDescriptionFragment.newInstance())
        transaction?.commit()
    }

    private fun setUpBundle(book: Book){
        setFragmentResult("getBookName", bundleOf("book_name" to book.name, "book_desc" to book.description))
    }
}
