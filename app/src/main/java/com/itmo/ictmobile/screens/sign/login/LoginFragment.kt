package com.itmo.ictmobile.screens.sign.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itmo.ictmobile.R

class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

}