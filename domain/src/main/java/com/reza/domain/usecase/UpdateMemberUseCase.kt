package com.reza.domain.usecase

import com.reza.domain.CommunityRepository
import com.reza.domain.model.MemberDomainModel
import javax.inject.Inject

interface UpdateMemberUseCase {
    suspend fun execute(
        memberDomainModel: MemberDomainModel
    )
}

class UpdateMemberUseCaseImpl @Inject constructor(
    private val communityRepository: CommunityRepository,
) : UpdateMemberUseCase {
    override suspend fun execute(
        memberDomainModel: MemberDomainModel
    ) = communityRepository.updateMember(memberDomainModel)
}