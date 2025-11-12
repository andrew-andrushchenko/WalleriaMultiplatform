package com.andrii_a.walleria.di

import com.andrii_a.walleria.data.remote.services.DesktopPhotoDownloader
import com.andrii_a.walleria.domain.services.PhotoDownloader
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val baseNetworkModule = module {
    single<HttpClient> {
        createHttpClient(
            preferencesRepository = get(),
            engine = OkHttp.create()
        )
    }

    singleOf(::DesktopPhotoDownloader) { bind<PhotoDownloader>() }
}