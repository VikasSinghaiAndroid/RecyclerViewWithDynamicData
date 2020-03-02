package com.example.samplewithall.di.component

import android.app.Application
import com.example.samplewithall.BaseApplication
import com.example.samplewithall.di.module.ActivityBuildersModule
import com.example.samplewithall.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    //We have to instruct the component how to build itself. We do this with the @Component.Builder annotation.

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}