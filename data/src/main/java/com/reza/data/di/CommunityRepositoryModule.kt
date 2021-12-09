package com.reza.data.di

import com.reza.data.CommunityLocalSource
import dagger.Module
import dagger.Provides
import dagger.Reusable
import com.reza.data.CommunityRemoteSource
import com.reza.data.mapper.MembersRepoDomainModelMapper
import com.reza.data.mapper.MembersRepoDomainModelMapperImpl
import com.reza.data.repository.CommunityRepositoryImpl
import com.reza.domain.CommunityRepository

@Module
class CommunityRepositoryModule {

    @Provides
    @Reusable
    fun provideCommunityRepository(
        communityRemoteSource: CommunityRemoteSource,
        communityLocalSource: CommunityLocalSource,
        membersRepoDomainModelMapper: MembersRepoDomainModelMapper
    ): CommunityRepository = CommunityRepositoryImpl(
        communityRemoteSource,
        communityLocalSource,
        membersRepoDomainModelMapper
    )


    @Provides
    @Reusable
    fun provideMembersRepositoryToDomainModelMapper(): MembersRepoDomainModelMapper =
        MembersRepoDomainModelMapperImpl()

}
