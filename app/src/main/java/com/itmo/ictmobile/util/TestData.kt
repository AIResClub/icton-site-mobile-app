package com.itmo.ictmobile.util

import com.itmo.ictmobile.R
import com.itmo.ictmobile.data.models.Club

object ClubsTestData {
    fun get(): List<Club> {
        return listOf(
            Club(
                "Проектный клуб факультета ИКТ Info Lab",
                R.raw.club_infolab,
            "https://vk.com/infolab_club"
            )
        )
    }
}
