package com.itmo.ictmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.itmo.ictmobile.screens.sign.SignActivity

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        authStateListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser

            if (user == null) {
                val i = Intent(this, SignActivity::class.java)
                startActivity(i)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        auth.addAuthStateListener(authStateListener)
    }
}