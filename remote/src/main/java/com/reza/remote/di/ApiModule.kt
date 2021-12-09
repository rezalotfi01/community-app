package com.reza.remote.di

import android.annotation.SuppressLint
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import com.reza.remote.ApiService
import com.reza.remote.data.CommunityRemoteSourceImpl
import com.reza.remote.mapper.*
import com.reza.data.CommunityRemoteSource
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideCommunityRemoteSource(
        apiService: ApiService,
        membersRepositoryMapper: MembersResponseToRepoMapper
    ): CommunityRemoteSource =
        CommunityRemoteSourceImpl(apiService, membersRepositoryMapper)


    @Reusable
    @Provides
    fun provideResponseToRepositoryModelMapper(): MembersResponseToRepoMapper =
        MembersResponseToRepoMapperImpl()


    @SuppressLint("ModuleCompanionObjects")
    @Module
    companion object {
        @Provides
        @JvmStatic
        @Singleton
        internal fun provideApi(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)

        @Provides
        @JvmStatic
        @Singleton
        internal fun provideRetrofit(
            httpBuilder: OkHttpClient.Builder,
            retrofitBuilder: Retrofit.Builder
        ): Retrofit = retrofitBuilder
            .client(httpBuilder.build())
            .build()
    }
}