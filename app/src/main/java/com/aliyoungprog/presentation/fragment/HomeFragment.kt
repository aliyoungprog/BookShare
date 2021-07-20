package com.aliyoungprog.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.aliyoungprog.R
import com.aliyoungprog.databinding.HomeFragmentBinding
import com.aliyoungprog.domain.entity.Book
import com.aliyoungprog.presentation.adapters.ItemClickListener
import com.aliyoungprog.presentation.adapters.NewBookAdapter
import com.aliyoungprog.presentation.vm.BooksViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment: Fragment(), ItemClickListener {


    var adapter: NewBookAdapter = NewBookAdapter(arrayListOf<Book>(), this, "HOME_FRAGMENT")

    private val bookViewModel: BooksViewModel by viewModel()
    lateinit var binding: HomeFragmentBinding

    companion object {
        fun getInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        (activity as AppCompatActivity).supportActionBar?.title = "Доступные книги"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.new_books.layoutManager = GridLayoutManager(context, 2)
        setUpViewModel()
        observeBooks()
        setUpSearchView()
    }


    private fun observeBooks() {
        progressBar.visibility = View.VISIBLE
        new_books.adapter = adapter
        bookViewModel.booksLiveData.observe(viewLifecycleOwner, Observer { it ->
            adapter.books = (it as ArrayList<Book>)
            adapter.notifyDataSetChanged()
            progressBar.visibility = View.GONE
        })
    }

    private fun setUpViewModel() {
        binding.viewModel = bookViewModel
        bookViewModel.getAllBooks()
    }


    // change to nav graph and pass arguments as safeargs
    override fun onItemClicked(book: Book) {
        setUpBundle(book)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val bundle = Bundle()
        bundle.putParcelable("book", book)
        val fragment = BookDescriptionFragment.newInstance()
        fragment.arguments = bundle
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun setUpBundle(book: Book) {
        setFragmentResult(
            "getBookName",
            bundleOf("book_name" to book.name, "book_desc" to book.description)
        )
    }

    private fun setUpSearchView() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.getFilter().filter(newText);
                return true
            }

        })
    }
}
