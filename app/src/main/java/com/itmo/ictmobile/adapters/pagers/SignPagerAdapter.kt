package com.itmo.ictmobile.adapters.pagers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.itmo.ictmobile.R
import com.itmo.ictmobile.screens.sign.login.LoginFragment
import com.itmo.ictmobile.screens.sign.register.RegisterFragment
import com.itmo.ictmobile.util.getString

class SignPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LoginFragment()
            else -> RegisterFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> getString(R.string.login)
            else -> getString(R.string.registration)
        }
    }
}