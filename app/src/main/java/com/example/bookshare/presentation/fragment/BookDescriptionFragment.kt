package com.example.bookshare.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResultListener
import com.example.bookshare.R
import com.example.bookshare.databinding.FragmentBookDescriptionBinding
import com.example.bookshare.domain.entity.Book
import timber.log.Timber

class BookDescriptionFragment : Fragment() {

    lateinit var binding: FragmentBookDescriptionBinding
    lateinit var bookName: TextView
    lateinit var bookSender: TextView
    lateinit var bookDesc: TextView
    lateinit var book: Book

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentBookDescriptionBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance() = BookDescriptionFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVars()
        receiveDataListener()
        setUpActionBar(book.name.toString())
    }

    private fun setUpActionBar(bookName: String) {
        (activity as AppCompatActivity).supportActionBar?.title = bookName
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun receiveDataListener(){
        val bundle: Bundle? = this.arguments
        book = bundle!!.getParcelable("book")!!
        bookName.text = book.name
        bookSender.text = book.sender
        bookDesc.text = book.description
    }

    override fun onDestroyView() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        super.onDestroyView()
    }

    private fun initVars(){
        bookName = binding.bookDescriptionName
        bookSender = binding.bookDescriptionAdd
        bookDesc = binding.bookDescriptionDesc
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val transaction = (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, HomeFragment.getInstance())
            transaction.commit()
            return true
        }
        return super.onOptionsItemSelected(item);
    }
}
