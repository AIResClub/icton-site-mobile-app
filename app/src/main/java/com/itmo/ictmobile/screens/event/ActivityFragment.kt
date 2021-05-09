package com.itmo.ictmobile.screens.event

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itmo.IctApp
import com.itmo.ictmobile.MainActivity
import com.itmo.ictmobile.R
import com.itmo.ictmobile.adapters.recycler.ClubAdapter
import com.itmo.ictmobile.data.models.User
import com.itmo.ictmobile.util.ClubsTestData
import com.itmo.ictmobile.util.Preferences
import com.itmo.ictmobile.util.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.club_fragment.*
import kotlinx.android.synthetic.main.login_fragment.*

class ActivityFragment : Fragment(R.layout.activity_fragment) {


    private lateinit var ActivityFragment: ClubAdapter

    override fun onStart() {
        super.onStart()


    }

}