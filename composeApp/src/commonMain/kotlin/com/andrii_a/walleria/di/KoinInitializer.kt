package com.andrii_a.walleria.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            baseNetworkModule,
            collectionsModule,
            mainDatabaseModule,
            platformDatabaseModule,
            platformDataStoreModule,
            loginModule,
            photosModule,
            appPreferencesModule,
            searchModule,
            topicsModule,
            userModule,
            accountAndSettingsModule
        )
    }
}