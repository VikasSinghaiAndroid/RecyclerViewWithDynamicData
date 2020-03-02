package com.languagexx.simplenotes.di

import androidx.lifecycle.ViewModel
import com.example.samplewithall.di.annotation.ViewModelKey
import com.example.samplewithall.viewmodels.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(notesViewModel: MainActivityViewModel): ViewModel
}
