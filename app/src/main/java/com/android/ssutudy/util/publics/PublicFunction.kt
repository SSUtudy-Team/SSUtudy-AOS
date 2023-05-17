package com.android.ssutudy.util.publics

import android.util.Log
import com.android.ssutudy.util.publics.PublicString.TAG

object PublicFunction {
    fun makeLog(msg: String) {
        Log.d(TAG, msg)
    }
}