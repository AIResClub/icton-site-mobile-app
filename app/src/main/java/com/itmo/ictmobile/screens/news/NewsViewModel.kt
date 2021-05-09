package com.itmo.ictmobile.screens.news

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.itmo.ictmobile.R
import com.itmo.ictmobile.data.models.NewsResponse
import com.itmo.ictmobile.data.remote.newsapi.NewsApiService
import com.itmo.ictmobile.util.Strings
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val disposable = CompositeDisposable()

    val isLoad = MutableLiveData<Boolean>()

    val information = MutableLiveData<NewsResponse>()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun fetchInformation(newsApi: NewsApiService?) {
        isLoad.value = true

        newsApi?.let {
            disposable.add(
                newsApi.getNews()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            isLoad.value = false
                            information.value = it
                        },
                        {
                            Toast.makeText(getApplication(), Strings.get(R.string.sign_failed), Toast.LENGTH_SHORT).show()
                        }
                    )
            )
        }
    }

}