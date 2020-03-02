package com.example.samplewithall.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.samplewithall.utils.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProvideFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}