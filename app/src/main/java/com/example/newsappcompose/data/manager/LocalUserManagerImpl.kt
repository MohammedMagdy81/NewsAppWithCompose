package com.example.newsappcompose.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsappcompose.data.manager.PreferencesKey.APP_ENTRY
import com.example.newsappcompose.domin.manager.LocalUserManager
import com.example.newsappcompose.utils.Constants.APP_ENTRY_KEY
import com.example.newsappcompose.utils.Constants.DATA_STORE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
) : LocalUserManager {

    override suspend fun saveAppEntry() {
        context.datastore.edit { settings ->
            settings[APP_ENTRY] = true
        }

    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map { preference ->
            preference[APP_ENTRY] ?: false
        }
    }
}

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

private object PreferencesKey {
    val APP_ENTRY = booleanPreferencesKey(name = APP_ENTRY_KEY)
}