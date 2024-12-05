package com.mawinda.composetest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ComposeTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        enableLogging()
    }

    private fun enableLogging() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }


}