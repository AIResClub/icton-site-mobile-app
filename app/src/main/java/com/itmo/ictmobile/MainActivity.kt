package com.itmo.ictmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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