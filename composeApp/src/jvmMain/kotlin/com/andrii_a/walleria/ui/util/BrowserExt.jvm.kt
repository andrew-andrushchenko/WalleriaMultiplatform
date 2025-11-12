package com.andrii_a.walleria.ui.util

import java.awt.Desktop
import java.net.URI

actual fun openInBrowser(url: String?) {
    if (url == null) return
    val desktop = Desktop.getDesktop()

    desktop.browse(URI(url))
}

