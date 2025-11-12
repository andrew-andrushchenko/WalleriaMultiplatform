package com.andrii_a.walleria

import android.app.Application
import com.andrii_a.walleria.di.initKoin
import multiplatform.network.cmptoast.AppContext
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class WalleriaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@WalleriaApplication)
            androidLogger()
        }
        AppContext.apply { set(applicationContext) }
    }

}