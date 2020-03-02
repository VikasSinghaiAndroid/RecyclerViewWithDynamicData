package com.example.samplewithall.di.module

import com.example.samplewithall.ui.MainActivity
import com.example.samplewithall.ui.SplashActivity
import com.languagexx.simplenotes.di.ViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


// declare all the activity here , dependency of activity are provided by this module

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class, FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

}