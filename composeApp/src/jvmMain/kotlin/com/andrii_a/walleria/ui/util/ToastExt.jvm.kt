package com.andrii_a.walleria.ui.util

import multiplatform.network.cmptoast.ToastDuration
import multiplatform.network.cmptoast.showToast

private val WToastDuration.toCmpWToastDuration
    get() = when (this) {
        WToastDuration.LONG -> ToastDuration.Long
        WToastDuration.SHORT -> ToastDuration.Short
    }

actual fun toast(message: String, duration: WToastDuration) {
    showToast(
        message = message,
        duration = duration.toCmpWToastDuration
    )
}