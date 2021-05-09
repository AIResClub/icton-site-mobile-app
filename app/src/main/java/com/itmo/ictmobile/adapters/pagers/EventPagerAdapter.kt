package com.itmo.ictmobile.adapters.pagers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.itmo.ictmobile.R
import com.itmo.ictmobile.screens.event.ActivityFragment
import com.itmo.ictmobile.screens.event.ProjectFragment
import com.itmo.ictmobile.util.Strings

class EventPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ActivityFragment()
            else -> ProjectFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> Strings.get(R.string.event)
            else -> Strings.get(R.string.project)
        }
    }
}