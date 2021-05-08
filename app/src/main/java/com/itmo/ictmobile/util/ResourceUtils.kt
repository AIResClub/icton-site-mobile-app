package com.itmo.ictmobile.util

import androidx.annotation.StringRes
import com.itmo.IctApp

object Strings {
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return IctApp.instance.getString(stringRes, *formatArgs)
    }
}
