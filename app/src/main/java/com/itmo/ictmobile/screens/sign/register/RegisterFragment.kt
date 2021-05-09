package com.itmo.ictmobile.screens.sign.register

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
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : Fragment(R.layout.register_fragment) {

    private lateinit var registerViewModel: RegisterViewModel

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        enterButton.setOnClickListener {
            if (!isFormIncorrect()) {
                enterButton.isEnabled = false
                loadingProgress.visibility = View.VISIBLE

                disposables.add(
                    registerViewModel.register(
                        User(
                            usernameEditText.text.toString(),
                            passwordEditText.text.toString(),
                            firstNameEditText.text.toString(),
                            secondNameEditText.text.toString(),
                            isStudentCheckBox.isChecked
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

        val firstNameValue = firstNameEditText.text.toString()
        if (firstNameValue.isEmpty() || firstNameValue.length >= 30) {
            isError = true
            toast(getString(R.string.incorrect_first_name))
        }

        val secondNameValue = secondNameEditText.text.toString()
        if (secondNameValue.isEmpty() || secondNameValue.length >= 30) {
            isError = true
            toast(getString(R.string.incorrect_second_name))
        }

        return isError
    }

}