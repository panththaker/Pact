package com.pact.app

import android.app.Application
import com.pact.di.initKoin

class PactApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}