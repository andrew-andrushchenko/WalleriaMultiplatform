package com.andrii_a.walleria.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.andrii_a.walleria.data.local.db.WalleriaDatabase
import org.koin.dsl.module
import java.io.File

actual val platformDatabaseModule = module {
    single<RoomDatabase.Builder<WalleriaDatabase>> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "walleria.db")

        Room.databaseBuilder<WalleriaDatabase>(
            name = dbFile.absolutePath,
        )
    }
}