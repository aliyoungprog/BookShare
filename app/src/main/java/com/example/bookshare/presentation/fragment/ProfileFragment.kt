package com.example.bookshare.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.bookshare.R
import com.example.bookshare.databinding.HomeFragmentBinding
import com.example.bookshare.databinding.ProfileFragmentBinding
import com.example.bookshare.presentation.fragment.book_view.ReadBooksFragment
import com.example.bookshare.presentation.fragment.book_view.WantToReadBooksFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.books_fragment_view_pager.*

class ProfileFragment: Fragment() {


    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var bind: ProfileFragmentBinding
    companion object{
        fun getInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = ProfileFragmentBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()
        setUpViewPager()
    }

    private fun setupTabs(){
        bind.fragmentMainTab.newTab()
                .let { bind.fragmentMainTab.addTab(it.setText(getString(R.string.books))) }
        bind.fragmentMainTab.newTab()
                .let { bind.fragmentMainTab.addTab(it.setText(getString(R.string.read))) }
    }

    private fun setUpViewPager(){
        pagerAdapter = PagerAdapter(childFragmentManager)
        viewPager = fragment_books_view_pager
        viewPager.adapter = pagerAdapter

        bind.fragmentBooksViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int,
            ) {
            }

            override fun onPageSelected(position: Int) {
                bind.fragmentMainTab.getTabAt(position)?.select()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })


        bind.fragmentMainTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    bind.fragmentBooksViewPager.currentItem = it
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}
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

