package com.andrii_a.walleria.ui.util

import android.content.Context
import androidx.core.net.toUri
import org.koin.core.context.GlobalContext

actual fun openInBrowser(url: String?) {
    if (url == null) return
    val koin = GlobalContext.get()
    val context: Context by koin.inject()

    CustomTabsHelper.openCustomTab(context, url.toUri())
}