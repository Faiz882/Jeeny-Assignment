package com.assignment.jeeny

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitHubRepoApp : Application(){

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}