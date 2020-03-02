package com.example.samplewithall

import com.example.samplewithall.di.component.DaggerAppComponent
import com.log4k.Level
import com.log4k.Log4k
import com.log4k.android.AndroidAppender
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Log4k.add(Level.Verbose, ".*", AndroidAppender())

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}