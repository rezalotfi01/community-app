package com.reza.domain.usecase

import com.reza.domain.CommunityRepository
import com.reza.domain.model.MemberDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMembersUseCase {
    suspend fun execute(
        page: Int,
    ): Flow<List<MemberDomainModel>>
}

class GetMembersUseCaseImpl @Inject constructor(
    private val communityRepository: CommunityRepository,
) : GetMembersUseCase {
    override suspend fun execute(
        page: Int,
    ): Flow<List<MemberDomainModel>> = communityRepository.getMembers(page)
}
