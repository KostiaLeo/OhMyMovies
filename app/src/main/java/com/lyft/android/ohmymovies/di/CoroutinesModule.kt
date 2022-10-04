package com.lyft.android.ohmymovies.di

import com.lyft.android.ohmymovies.utils.DefaultDispatcherProvider
import com.lyft.android.ohmymovies.utils.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {

    @Binds
    @Singleton
    fun bindDispatcherProvider(defaultDispatcherProvider: DefaultDispatcherProvider): DispatcherProvider
}