package com.example.bookshare.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.bookshare.R
import com.example.bookshare.databinding.FragmentSettingsBinding
import com.example.bookshare.presentation.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.context.stopKoin


class SettingsFragment : Fragment() {

    lateinit var bind: FragmentSettingsBinding

    companion object{
        fun getInstance() = SettingsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentSettingsBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActionBar()
        logoutBtnListener()
    }

    private fun logout(){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(context, LoginActivity::class.java))
        stopKoin()
        activity?.finish()
    }

    private fun logoutBtnListener(){
        bind.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun setUpActionBar() {
        (activity as AppCompatActivity).supportActionBar?.title = "Настройки"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroyView() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        super.onDestroyView()
    }
}