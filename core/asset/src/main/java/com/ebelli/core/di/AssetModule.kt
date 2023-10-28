package com.ebelli.core.di

import android.content.Context
import com.ebelli.core.asset.AssetHelper
import com.ebelli.core.asset.AssetHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AssetModule {
    @Provides
    @Singleton
    fun provideAssetHelper(@ApplicationContext context: Context) : AssetHelper = AssetHelperImpl(context)
}