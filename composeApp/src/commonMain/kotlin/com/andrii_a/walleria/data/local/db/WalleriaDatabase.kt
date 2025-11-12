package com.andrii_a.walleria.data.local.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.andrii_a.walleria.data.local.db.dao.SearchHistoryDao
import com.andrii_a.walleria.data.local.db.entities.SearchHistoryEntity

@Database(
    entities = [SearchHistoryEntity::class],
    version = 1
)
@ConstructedBy(WalleriaDatabaseConstructor::class)
abstract class WalleriaDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object WalleriaDatabaseConstructor : RoomDatabaseConstructor<WalleriaDatabase> {
    override fun initialize(): WalleriaDatabase
}