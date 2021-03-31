package com.example.bookshare.presentation.fragment.book_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookshare.databinding.ReadBooksFragmentBinding

class ReadBooksFragment : Fragment() {

    lateinit var bind: ReadBooksFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        bind = ReadBooksFragmentBinding.inflate(inflater)
        return bind.root
    }
}