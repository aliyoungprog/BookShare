package com.example.bookshare.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.bookshare.presentation.fragment.book_view.ReadBooksFragment
import com.example.bookshare.presentation.fragment.book_view.WantToReadBooksFragment

class PagerAdapter  (fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        if (position == 1)
            return ReadBooksFragment()
        return WantToReadBooksFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "OBJECT ${(position + 1)}"
    }
}