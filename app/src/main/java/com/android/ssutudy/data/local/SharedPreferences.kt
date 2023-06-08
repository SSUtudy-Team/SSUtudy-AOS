package com.android.ssutudy.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.android.ssutudy.BuildConfig
import com.android.ssutudy.util.publics.PublicString.PREFERENCE_NAME

object SharedPreferences {
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        val masterKeyAlias = MasterKey
            .Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        preferences = if (BuildConfig.DEBUG) context.getSharedPreferences(
            "${context.packageName}_${PREFERENCE_NAME}",
            Context.MODE_PRIVATE
        )
        else EncryptedSharedPreferences.create(
            context,
            "${context.packageName}_${PREFERENCE_NAME}",
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun setString(key: String, value: String?) {
        preferences.edit {
            putString(key, value)
        }
    }

    fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    fun clear() {
        preferences.edit {
            clear()
        }
    }
}