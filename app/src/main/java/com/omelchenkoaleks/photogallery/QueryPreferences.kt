package com.omelchenkoaleks.photogallery

import android.content.Context
import androidx.preference.PreferenceManager

/**
 * Ключ для хранения запроса.
 * Применяется во всех операциях чтения или записи запроса.
 */
private const val PREF_SEARCH_QUERY = "searchQuery"

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
            .edit()
            .putString(PREF_SEARCH_QUERY, query)
            .apply()
    }

}