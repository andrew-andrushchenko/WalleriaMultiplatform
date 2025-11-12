package com.andrii_a.walleria.data.local.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.andrii_a.walleria.domain.AppTheme
import com.andrii_a.walleria.domain.PhotoQuality
import com.andrii_a.walleria.domain.repository.LocalPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.io.IOException

class LocalPreferencesRepositoryImpl(private val dataStore: DataStore<Preferences>) : LocalPreferencesRepository {

    private val preferencesFlow = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }

    override val appTheme: Flow<AppTheme> = preferencesFlow.map { preferences ->
        AppTheme.valueOf(
            preferences[WalleriaAppPreferencesKeys.APP_THEME] ?: AppTheme.SYSTEM_DEFAULT.name
        )
    }

    override val photosLoadQuality: Flow<PhotoQuality> = preferencesFlow.map { preferences ->
        PhotoQuality.valueOf(
            preferences[WalleriaAppPreferencesKeys.PHOTOS_LOAD_QUALITY] ?: PhotoQuality.MEDIUM.name
        )
    }
    override val photosDownloadQuality: Flow<PhotoQuality> = preferencesFlow.map { preferences ->
        PhotoQuality.valueOf(
            preferences[WalleriaAppPreferencesKeys.PHOTOS_DOWNLOAD_QUALITY] ?: PhotoQuality.MEDIUM.name
        )
    }

    override suspend fun updateAppTheme(appTheme: AppTheme) {
        dataStore.edit { preferences ->
            preferences[WalleriaAppPreferencesKeys.APP_THEME] = appTheme.name
        }
    }

    override suspend fun updatePhotosLoadQuality(photoQuality: PhotoQuality) {
        dataStore.edit { preferences ->
            preferences[WalleriaAppPreferencesKeys.PHOTOS_LOAD_QUALITY] =
                photoQuality.name
        }
    }

    override suspend fun updatePhotosDownloadQuality(photoQuality: PhotoQuality) {
        dataStore.edit { preferences ->
            preferences[WalleriaAppPreferencesKeys.PHOTOS_DOWNLOAD_QUALITY] =
                photoQuality.name
        }
    }

    object WalleriaAppPreferencesKeys {
        val APP_THEME = stringPreferencesKey("app_theme")
        val PHOTOS_LOAD_QUALITY = stringPreferencesKey("photos_load_quality")
        val PHOTOS_DOWNLOAD_QUALITY = stringPreferencesKey("photos_download_quality")
    }
}