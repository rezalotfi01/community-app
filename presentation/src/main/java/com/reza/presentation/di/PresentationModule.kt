package com.reza.presentation.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import com.reza.presentation.mapper.*

@Module
class PresentationModule {

    @Provides
    @Reusable
    fun provideMembersDomainToUiModelMapper(
    ): MembersDomainUiModelMapper = MembersDomainUiModelMapperImpl()

}