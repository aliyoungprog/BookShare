package com.example.bookshare.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bookshare.R
import com.example.bookshare.presentation.fragment.HomeFragment
import com.example.bookshare.presentation.fragment.ProfileFragment
import com.example.bookshare.presentation.fragment.AddBookMainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = (this.getString(R.string.available_books))
    }

    override fun onStart() {
        super.onStart()
        setupBottomNavBar()
    }

    private fun changeFragment(fragmentObject: Fragment) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.nav_host_fragment, fragmentObject)
        fragmentManager.commit()
    }

    private fun setupBottomNavBar() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    changeFragment(HomeFragment.getInstance())
                    supportActionBar?.setTitle(this.getString(R.string.available_books))
                }
                R.id.add_book -> {
                    changeFragment(AddBookMainFragment.getInstance())
                    supportActionBar?.setTitle("Добавить книгу")
                }
                R.id.action_profile -> {
                    changeFragment(ProfileFragment.getInstance())
                    supportActionBar?.setTitle(this.getString(R.string.profile))
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

}
