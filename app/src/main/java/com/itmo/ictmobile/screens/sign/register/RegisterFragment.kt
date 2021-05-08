package com.itmo.ictmobile.screens.sign.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itmo.ictmobile.R

class RegisterFragment : Fragment(R.layout.register_fragment) {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

}