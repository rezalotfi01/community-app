package com.reza.local.di

import android.content.Context
import com.reza.data.CommunityRemoteSource
import com.reza.local.AppDatabase
import com.reza.local.converter.Converters
import com.reza.local.dao.MemberDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        AppDatabase.create(context)

    @Provides
    fun provideMemberDao(database: AppDatabase): MemberDao {
        return database.memberDao()
    }

    @Provides
    fun provideConverters(): Converters = Converters()

}