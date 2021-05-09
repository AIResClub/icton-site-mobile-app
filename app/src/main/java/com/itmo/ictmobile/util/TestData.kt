package com.itmo.ictmobile.util

import com.itmo.ictmobile.R
import com.itmo.ictmobile.data.models.Club

object ClubsTestData {
    fun get(): List<Club> {
        return listOf(
            Club(
                "Info Lab",
                R.raw.club_infolab,
                "https://vk.com/infolab_club"
            ),
            Club(
                "AIRes",
                R.raw.club_aires,
                "https://vk.com/airesclub"
            ),
            Club(
                "DesArm",
                R.raw.club_desarm,
                "https://vk.com/desarm_itmo"
            ),
            Club(
                "ITMeladze",
                R.raw.club_meladze,
                "https://vk.com/itmeladze"
            )
        )
    }
}
