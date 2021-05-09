package com.itmo.ictmobile.data.remote.newsapi

import com.itmo.ictmobile.data.models.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface NewsApiService {

    // https://newsapi.org/v2/everything?q=bitcoin&pagesize=20&language=ru&apiKey=6c5f919116174864b26e286fe608aaf7

    @GET("everything?q=bitcoin&pagesize=20&language=ru&apiKey=6c5f919116174864b26e286fe608aaf7")
    fun getNews(): Single<NewsResponse>

}