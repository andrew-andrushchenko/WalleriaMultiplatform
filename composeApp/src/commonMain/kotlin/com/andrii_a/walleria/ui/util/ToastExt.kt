package com.andrii_a.walleria.ui.util

enum class WToastDuration {
    LONG, SHORT
}

expect fun toast(
    message: String,
    duration: WToastDuration = WToastDuration.SHORT
)