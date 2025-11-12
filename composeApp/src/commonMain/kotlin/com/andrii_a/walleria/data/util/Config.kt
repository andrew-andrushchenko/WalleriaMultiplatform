package com.andrii_a.walleria.data.util

import com.andrii_a.walleria.BuildConfig

internal expect val authCallback: String

object Config {
    const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    const val CLIENT_SECRET = BuildConfig.UNSPLASH_SECRET_KEY
    val AUTH_CALLBACK = authCallback
    const val AUTH_GRANT_TYPE = "authorization_code"

    private val AUTH_SCOPES = AuthScopes.entries.joinToString("+") { it.scope }

    val LOGIN_URL = buildString {
        append("https://unsplash.com/oauth/authorize")
        append("?client_id=$CLIENT_ID")
        append("&redirect_uri=$authCallback")
        append("&response_type=code")
        append("&scope=$AUTH_SCOPES")
    }

    const val JOIN_URL = "https://unsplash.com/join"

    val PAGE_SIZE: Int = BuildConfig.PAGINATION_PAGE_SIZE.toInt()
    const val INITIAL_PAGE_INDEX = 1
}