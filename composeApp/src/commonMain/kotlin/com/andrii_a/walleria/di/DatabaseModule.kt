package com.andrii_a.walleria.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.andrii_a.walleria.data.local.db.WalleriaDatabase
import com.andrii_a.walleria.data.local.db.dao.SearchHistoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformDatabaseModule: Module

val mainDatabaseModule = module {
    single<WalleriaDatabase> { getRoomDatabase(get()) }

    factory<SearchHistoryDao> { getSearchHistoryDao(get()) }
}

private fun getRoomDatabase(builder: RoomDatabase.Builder<WalleriaDatabase>): WalleriaDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .fallbackToDestructiveMigration(dropAllTables = false)
        .build()
}

private fun getSearchHistoryDao(database: WalleriaDatabase): SearchHistoryDao {
    return database.searchHistoryDao()
}
