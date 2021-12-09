package com.reza.domain.di

import com.reza.domain.CommunityRepository
import com.reza.domain.usecase.GetMembersUseCase
import com.reza.domain.usecase.GetMembersUseCaseImpl
import com.reza.domain.usecase.UpdateMemberUseCase
import com.reza.domain.usecase.UpdateMemberUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class DomainModule {
    @Provides
    @Reusable
    fun provideGetMembers(
        communityRepository: CommunityRepository,
    ): GetMembersUseCase = GetMembersUseCaseImpl(communityRepository)

    @Provides
    @Reusable
    fun provideUpdateMember(
        communityRepository: CommunityRepository,
    ): UpdateMemberUseCase = UpdateMemberUseCaseImpl(communityRepository)

}
