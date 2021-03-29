package com.example.bookshare.presentation.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.bookshare.R
import com.example.bookshare.data.repository.BookRepositoryImpl
import com.example.bookshare.databinding.FragmentBookDescriptionBinding
import com.example.bookshare.presentation.vm.BookViewModel
import org.koin.android.ext.android.bind

class BookDescriptionFragment : Fragment() {

    lateinit var binding: FragmentBookDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBookDescriptionBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance() = BookDescriptionFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        receiveDataListener()
    }

    private fun setUpActionBar(bookName: String) {
        (activity as AppCompatActivity).supportActionBar?.title = bookName
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun receiveDataListener(){
        setFragmentResultListener("getBookName") { _, bundle ->
            val bookName = if (bundle.getString("book_name") != null) bundle.getString("book_name")!! else "Описание"
            setUpActionBar(bookName)
        }
    }

    override fun onDestroyView() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        super.onDestroyView()
    }
}