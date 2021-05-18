package com.example.bookshare.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.bookshare.R
import com.example.bookshare.data.database.MyFirebaseAuth
import com.example.bookshare.databinding.ProfileFragmentBinding
import com.example.bookshare.presentation.adapters.PagerAdapter
import com.example.bookshare.presentation.vm.UserViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.books_fragment_view_pager.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment: Fragment() {

    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var bind: ProfileFragmentBinding
    private val userViewModel: UserViewModel by viewModel()

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
        settingsBtnListener()
        setViewModel()
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

    private fun changeFragment(fragmentObject: Fragment) {
        val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
        fragmentManager?.replace(R.id.nav_host_fragment, fragmentObject)
        fragmentManager?.commit()
    }

    private fun settingsBtnListener(){
        bind.bntSettings.setOnClickListener {
            changeFragment(SettingsFragment.getInstance())
        }
    }

    private fun setViewModel(){
        val email = MyFirebaseAuth.db_auth.currentUser!!.email!!
        userViewModel.getUserByEmail(email)
        userViewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                //bind.userEmail.text = "Аккаунт: ".plus(it.email)
                if (it.telegram_account != null)
                    bind.userInsta.text = "Insta: ".plus(it.instagram_account)
                if (it.instagram_account != null)
                    bind.userTelega.text = "Telega: ".plus(it.telegram_account)
                bind.myBooksCount.text = if (it.myBooks != null) "${it.myBooks.size} книг" else "0 книг"
            }
        })
    }
}


