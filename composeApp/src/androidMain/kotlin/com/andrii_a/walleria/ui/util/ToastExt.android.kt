package com.andrii_a.walleria.ui.util

import android.content.Context
import android.widget.Toast
import org.koin.core.context.GlobalContext

private val WToastDuration.toAndroidToastDuration
    get() = when (this) {
        WToastDuration.LONG -> Toast.LENGTH_LONG
        WToastDuration.SHORT -> Toast.LENGTH_SHORT
    }

actual fun toast(message: String, duration: WToastDuration) {
    val koin = GlobalContext.get()
    val context: Context by koin.inject()

    Toast.makeText(context, message, duration.toAndroidToastDuration).show()
}