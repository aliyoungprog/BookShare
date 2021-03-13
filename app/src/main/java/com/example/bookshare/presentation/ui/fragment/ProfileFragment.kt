package com.example.bookshare.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookshare.databinding.ProfileFragmentBinding

class ProfileFragment: Fragment() {

    private var bind: ProfileFragmentBinding? = null

    companion object{
        fun getInstance() = ProfileFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = ProfileFragmentBinding.inflate(inflater, container, false)
        return bind!!.root
    }
}
