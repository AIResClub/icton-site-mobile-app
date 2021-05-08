package com.itmo

import android.app.Application

class IctApp : Application() {

    companion object {
        lateinit var instance: IctApp private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}