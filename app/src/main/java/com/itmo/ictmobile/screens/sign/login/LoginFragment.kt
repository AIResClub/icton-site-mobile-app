package com.itmo.ictmobile.screens.sign.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itmo.IctApp
import com.itmo.ictmobile.MainActivity
import com.itmo.ictmobile.R
import com.itmo.ictmobile.data.models.User
import com.itmo.ictmobile.util.Preferences
import com.itmo.ictmobile.util.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var loginViewModel: LoginViewModel

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        enterButton.setOnClickListener {
            if (!isFormIncorrect()) {
                enterButton.isEnabled = false
                loadingProgress.visibility = View.VISIBLE

                disposables.add(
                    loginViewModel.login(
                        User(
                            usernameEditText.text.toString(),
                            passwordEditText.text.toString()
                        )
                    )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { user ->
                                Preferences(IctApp.sharedPreferences).saveUser(user)
                                startActivity(Intent(activity, MainActivity::class.java))
                                activity?.finish()
                            },
                            { e ->
                                enterButton.isEnabled = true
                                loadingProgress.visibility = View.GONE
                                toast(getString(R.string.sign_failed))
                            }
                        )
                )
            }
        }
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun isFormIncorrect(): Boolean {
        var isError = false

        val usernameValue = usernameEditText.text.toString()
        if (usernameValue.isEmpty() || usernameValue.length >= 30) {
            isError = true
            toast(getString(R.string.incorrect_username))
        }

        val passwordValue = passwordEditText.text.toString()
        if (passwordValue.isEmpty() || passwordValue.length < 7 || passwordValue.length >= 30) {
            isError = true
            toast(getString(R.string.incorrect_password))
        }

        return isError
    }

}