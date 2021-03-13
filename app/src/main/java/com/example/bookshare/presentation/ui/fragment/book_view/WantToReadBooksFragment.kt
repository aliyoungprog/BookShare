package com.example.bookshare.presentation.ui.fragment.book_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookshare.databinding.WantToBooksFragmentBinding

class WantToReadBooksFragment : Fragment() {

    private var bind: WantToBooksFragmentBinding? = null

    companion object {
        fun getInstance() = WantToReadBooksFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        bind = WantToBooksFragmentBinding.inflate(inflater, container, false)
        return bind!!.root
    }


}
