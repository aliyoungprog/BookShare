package com.aliyoungprog.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aliyoungprog.presentation.fragment.book_view.MyBooksFragment
import com.aliyoungprog.presentation.fragment.book_view.WantToReadBooksFragment

class PagerAdapter  (fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        if (position == 1)
            return WantToReadBooksFragment()
        return MyBooksFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "OBJECT ${(position + 1)}"
    }
}
