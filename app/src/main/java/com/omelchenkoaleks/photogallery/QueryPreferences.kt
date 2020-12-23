package com.omelchenkoaleks.photogallery

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

/**
 * Ключ для хранения запроса.
 * Применяется во всех операциях чтения или записи запроса.
 */
private const val PREF_SEARCH_QUERY = "searchQuery"

private const val PREF_LAST_RESULT_ID = "lastResultId"

/**
 * Приложению нужен только один экземпляр QueryPreferences, который может использоваться
 * всеми другими компонентами. Поэтому object.
 */
object QueryPreferences {

    fun getStoredQuery(context: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(PREF_SEARCH_QUERY, "")!!
    }

    fun setStoredQuery(context: Context, query: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit {
                putString(PREF_SEARCH_QUERY, query)
            }
    }

    fun getLastResultId(context: Context): String {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(PREF_LAST_RESULT_ID, "")!!
    }

    fun setLastResultId(context: Context, lastResultId: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit {
            putString(PREF_LAST_RESULT_ID, lastResultId)
        }
    }

}