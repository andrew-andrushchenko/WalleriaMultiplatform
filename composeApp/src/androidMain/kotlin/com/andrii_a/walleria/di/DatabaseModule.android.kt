package com.andrii_a.walleria.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.andrii_a.walleria.data.local.db.WalleriaDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformDatabaseModule = module {
    single<RoomDatabase.Builder<WalleriaDatabase>> {
        val appContext = androidContext().applicationContext
        val dbFile = appContext.getDatabasePath("walleria.db")

        Room.databaseBuilder<WalleriaDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}