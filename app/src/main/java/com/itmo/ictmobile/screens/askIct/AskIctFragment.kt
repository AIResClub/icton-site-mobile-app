package com.itmo.ictmobile.screens.askIct

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itmo.ictmobile.R
import io.reactivex.disposables.CompositeDisposable

class AskIctFragment : Fragment(R.layout.askict_fragment) {

    private lateinit var askIctViewModel: AskIctViewModel

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askIctViewModel = ViewModelProviders.of(this).get(AskIctViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        // TODO: BUTTONS
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

}