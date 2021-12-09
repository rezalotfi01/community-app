package com.reza.domain

import kotlinx.coroutines.flow.Flow
import com.reza.domain.model.MemberDomainModel

interface CommunityRepository {
    suspend fun getMembers(page: Int): Flow<List<MemberDomainModel>>

    suspend fun updateMember(member: MemberDomainModel)
}