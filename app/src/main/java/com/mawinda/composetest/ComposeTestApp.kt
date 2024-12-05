package com.mawinda.composetest

import android.app.Application
import timber.log.Timber

class ComposeTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        enableLogging()
    }


    private fun enableLogging() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }


}