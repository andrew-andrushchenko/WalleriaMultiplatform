package com.andrii_a.walleria

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform