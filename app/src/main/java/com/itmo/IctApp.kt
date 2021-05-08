package com.itmo

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore

class IctApp : Application() {

    companion object {
        lateinit var instance: IctApp private set
        lateinit var database: FirebaseFirestore private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = FirebaseFirestore.getInstance()
    }

}