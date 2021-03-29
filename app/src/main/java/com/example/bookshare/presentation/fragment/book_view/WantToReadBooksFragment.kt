package com.example.bookshare.presentation.fragment.book_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookshare.databinding.HomeFragmentBinding
import com.example.bookshare.databinding.WantToBooksFragmentBinding

class WantToReadBooksFragment : Fragment() {

    companion object {
        fun getInstance() = WantToReadBooksFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = WantToBooksFragmentBinding.inflate(inflater)
        return binding.root
    }
}
