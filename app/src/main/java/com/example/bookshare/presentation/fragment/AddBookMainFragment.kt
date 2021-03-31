package com.example.bookshare.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookshare.databinding.BookAddFragmentBinding
import com.example.bookshare.databinding.BooksFragmentViewPagerBinding

class AddBookMainFragment : Fragment() {


    private lateinit var bind: BookAddFragmentBinding


    companion object {
        fun getInstance() = AddBookMainFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        bind = BookAddFragmentBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}