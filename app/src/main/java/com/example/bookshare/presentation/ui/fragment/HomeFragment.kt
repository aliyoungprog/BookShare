package com.example.bookshare.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookshare.R
import com.example.bookshare.data.disk_data_source.Book
import com.example.bookshare.databinding.HomeFragmentBinding
import com.example.bookshare.presentation.ui.adapters.NewBookAdapter
import kotlinx.android.synthetic.main.home_fragment.view.*

class HomeFragment: Fragment() {

    private var bind: HomeFragmentBinding? = null

    private val binding get() = bind!!
    lateinit var adapter: NewBookAdapter


    companion object{
        fun getInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = HomeFragmentBinding.inflate(inflater, container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf(Book("Test", "Test", "Test"))
        adapter = NewBookAdapter(list)
        view.new_books.layoutManager = LinearLayoutManager(context)
        view.new_books.adapter = adapter
    }


}
