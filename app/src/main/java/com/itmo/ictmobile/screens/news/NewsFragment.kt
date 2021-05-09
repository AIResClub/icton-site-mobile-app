package com.itmo.ictmobile.screens.news

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.itmo.IctApp
import com.itmo.ictmobile.R
import com.itmo.ictmobile.adapters.recycler.NewAdapter
import com.itmo.ictmobile.data.models.ArticlesItem
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : Fragment(R.layout.news_fragment) {

    private lateinit var newsViewModel: NewsViewModel

    private lateinit var newsAdapter: NewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        news_rv.setHasFixedSize(true)

        newsAdapter = NewAdapter()

        news_rv.adapter = newsAdapter
    }

    override fun onResume() {
        super.onResume()

        newsViewModel.isLoad.observe(this, Observer {
            progressBar.isVisible = it
        })

        newsViewModel.information.observe(this, Observer {
            newsAdapter.updateNews(it.articles as List<ArticlesItem>)
        })

        newsViewModel.fetchInformation(IctApp.newsApi)
    }

}