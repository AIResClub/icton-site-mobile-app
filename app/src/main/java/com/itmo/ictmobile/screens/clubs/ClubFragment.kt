package com.itmo.ictmobile.screens.clubs

import androidx.fragment.app.Fragment
import com.itmo.ictmobile.R
import com.itmo.ictmobile.adapters.recycler.ClubAdapter
import com.itmo.ictmobile.util.ClubsTestData
import kotlinx.android.synthetic.main.club_fragment.*

class ClubFragment : Fragment(R.layout.club_fragment) {

    private lateinit var clubAdapter: ClubAdapter

    override fun onStart() {
        super.onStart()

        clubs_rv.setHasFixedSize(true)
        clubAdapter = ClubAdapter()
        clubs_rv.adapter = clubAdapter

        clubAdapter.addClubs(ClubsTestData.get())

    }

}