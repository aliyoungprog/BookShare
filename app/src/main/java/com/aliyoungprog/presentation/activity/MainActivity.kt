package com.aliyoungprog.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aliyoungprog.R
import com.aliyoungprog.presentation.fragment.HomeFragment
import com.aliyoungprog.presentation.fragment.ProfileFragment
import com.aliyoungprog.presentation.fragment.AddBookMainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                }
                R.id.add_book -> {
                    changeFragment(AddBookMainFragment.getInstance())
                }
                R.id.action_profile -> {
                    changeFragment(ProfileFragment.getInstance())
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
