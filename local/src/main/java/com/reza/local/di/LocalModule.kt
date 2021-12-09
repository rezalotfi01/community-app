package com.reza.local.di

import com.reza.data.CommunityLocalSource
import com.reza.local.dao.MemberDao
import com.reza.local.data.CommunityLocalSourceImpl
import com.reza.local.mapper.MembersEntityRepoMapper
import com.reza.local.mapper.MembersEntityRepoMapperImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class LocalModule {
    @Singleton
    @Provides
    fun provideCommunityLocalSource(
        dao: MemberDao,
        memberMapper: MembersEntityRepoMapper
    ): CommunityLocalSource =
        CommunityLocalSourceImpl(dao, memberMapper)


    @Reusable
    @Provides
    fun provideEntityRepositoryModelMapper(): MembersEntityRepoMapper =
        MembersEntityRepoMapperImpl()
}