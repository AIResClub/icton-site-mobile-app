package com.itmo

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.firestore.FirebaseFirestore
import com.itmo.ictmobile.util.Preferences

class IctApp : Application() {

    companion object {
        lateinit var instance: IctApp private set
        lateinit var database: FirebaseFirestore private set
        lateinit var sharedPreferences: SharedPreferences private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = FirebaseFirestore.getInstance()
        sharedPreferences = getSharedPreferences(Preferences.PREF_NAME, Context.MODE_PRIVATE)
    }

}