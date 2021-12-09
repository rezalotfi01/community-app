package com.reza.data

import com.reza.data.model.MemberRepositoryModel

interface CommunityLocalSource {
    suspend fun saveMembersIfNotExists(members: List<MemberRepositoryModel>): List<MemberRepositoryModel>
    suspend fun updateSavedMember(member: MemberRepositoryModel)
}