package com.itmo.ictmobile.screens.clubs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itmo.ictmobile.R
import io.reactivex.disposables.CompositeDisposable

class ClubFragment : Fragment(R.layout.club_fragment) {

    private lateinit var clubViewModel: ClubViewModel

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clubViewModel = ViewModelProviders.of(this).get(ClubViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        // TODO: BUTTONS
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

}