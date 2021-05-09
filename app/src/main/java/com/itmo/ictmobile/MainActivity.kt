package com.itmo.ictmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.itmo.IctApp
import com.itmo.ictmobile.screens.sign.SignActivity
import com.itmo.ictmobile.util.Preferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        goToAuthIfLoggedOut()

        initBottomMenu()

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
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

    private fun initBottomMenu() {
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = host.navController

        bottom_navigation_view.setupWithNavController(navController)
    }
}