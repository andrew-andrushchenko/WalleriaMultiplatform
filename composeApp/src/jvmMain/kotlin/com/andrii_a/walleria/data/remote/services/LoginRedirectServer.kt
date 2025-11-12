package com.andrii_a.walleria.data.remote.services

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

internal fun startRedirectServer(onCodeObtained: (String) -> Unit) =
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/callback") {
                val code = call.request.queryParameters["code"]
                if (code != null) {
                    call.respondText("You can close this window.")
                    onCodeObtained(code)
                } else {
                    call.respondText("No code received.")
                }
            }
        }
    }.also { it.start(wait = false) }