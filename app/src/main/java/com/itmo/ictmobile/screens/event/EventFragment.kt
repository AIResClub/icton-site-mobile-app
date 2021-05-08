package com.itmo.ictmobile.screens.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itmo.ictmobile.R
import io.reactivex.disposables.CompositeDisposable

class EventFragment : Fragment(R.layout.event_fragment) {

    private lateinit var eventViewModel: EventViewModel

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)
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