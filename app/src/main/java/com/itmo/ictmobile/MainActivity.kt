package com.itmo.ictmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.itmo.IctApp
import com.itmo.ictmobile.screens.sign.SignActivity
import com.itmo.ictmobile.util.Preferences

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToAuthIfLoggedOut()

    }

    private fun isAuth(): Boolean {
        if (Preferences(IctApp.sharedPreferences).getUser() == null) {
            return false
        }
        return true
    }

    private fun goToAuthIfLoggedOut() {
        if (!isAuth()) {
            startActivity(Intent(this, SignActivity::class.java))
            finish()
        }
    }
}