package com.canbazdev.datastorekiller

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.canbazdev.datastorekiller.Constants.USER_PREFERENCES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(USER_PREFERENCES)

class DataStoreRepository(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        private val SAVED_NAME = stringPreferencesKey("name")
        private val SAVED_AGE = intPreferencesKey("age")
    }

    suspend fun setName(newName: String) {
        dataStore.edit { preferences ->
            preferences[SAVED_NAME] = newName
        }
    }

    val getName: Flow<String> = dataStore.data.map {
        it[SAVED_NAME] ?: "unnamed"
    }

    suspend fun setAge(newAge: Int) {
        dataStore.edit { preferences ->
            preferences[SAVED_AGE] = newAge
        }
    }

    val getAge: Flow<Int> = dataStore.data.map {
        it[SAVED_AGE] ?: 17
    }

}