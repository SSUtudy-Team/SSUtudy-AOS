package com.android.ssutudy

import android.app.Application
import com.android.ssutudy.data.local.SharedPreferences

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        initPreferences()
    }

    private fun initPreferences() {
        SharedPreferences.init(this)
    }
}