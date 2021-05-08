package com.itmo.ictmobile.screens.sign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itmo.ictmobile.R
import com.itmo.ictmobile.adapters.pagers.SignPagerAdapter
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val fragmentAdapter = SignPagerAdapter(supportFragmentManager)
        viewpager_sign.adapter = fragmentAdapter

        tabs_sign.setupWithViewPager(viewpager_sign)
    }

}