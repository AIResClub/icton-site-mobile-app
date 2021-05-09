package com.itmo

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.firestore.FirebaseFirestore
import com.itmo.ictmobile.data.remote.newsapi.NewsApiService
import com.itmo.ictmobile.util.Preferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class IctApp : Application() {

    private val baseUrl = "https://newsapi.org/v2/"

    companion object {
        lateinit var instance: IctApp private set
        lateinit var database: FirebaseFirestore private set
        lateinit var sharedPreferences: SharedPreferences private set
        lateinit var newsApi: NewsApiService
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = FirebaseFirestore.getInstance()
        sharedPreferences = getSharedPreferences(Preferences.PREF_NAME, Context.MODE_PRIVATE)

        configureRetrofit()
    }

    private fun configureRetrofit() {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        newsApi = retrofit.create(NewsApiService::class.java)
    }

}