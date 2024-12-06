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

    /**
     * Enable logging for debug builds using timber.
     */
    private fun enableLogging() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}