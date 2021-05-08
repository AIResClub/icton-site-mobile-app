package com.itmo.ictmobile.util

import android.content.SharedPreferences
import com.google.gson.Gson
import com.itmo.ictmobile.data.models.User

class Preferences(private val sharedPreferences: SharedPreferences) {

    private val gson = Gson()

    fun saveUser(user: User) {
        val prefsEditor = sharedPreferences.edit()
        val json = gson.toJson(user)
        prefsEditor.putString(USER_AUTH, json)
        prefsEditor.apply()
    }

    fun getUser(): User? {
        val json = sharedPreferences.getString(USER_AUTH, null)
        return gson.fromJson(json, User::class.java)
    }

    fun removeUser() {
        sharedPreferences.edit().remove(USER_AUTH).apply()
    }

    companion object {
        const val USER_AUTH = "USER_AUTH"
        const val PREF_NAME = "PREF_NAME"
    }

}