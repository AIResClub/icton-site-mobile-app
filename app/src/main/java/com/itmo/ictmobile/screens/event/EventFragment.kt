package com.itmo.ictmobile.screens.event

import androidx.fragment.app.Fragment
import com.itmo.ictmobile.R
import com.itmo.ictmobile.adapters.pagers.EventPagerAdapter
import kotlinx.android.synthetic.main.event_fragment.*

class EventFragment : Fragment(R.layout.event_fragment) {


    override fun onStart() {
        super.onStart()

        val fragmentAdapter = EventPagerAdapter(childFragmentManager)
        viewpager_event.adapter = fragmentAdapter

        tabs_event.setupWithViewPager(viewpager_event)

    }

}