package com.reza.data

import com.reza.data.model.MemberRepositoryModel
import kotlinx.coroutines.flow.Flow

interface CommunityRemoteSource {

    suspend fun getAllMembers(page: Int): Flow<List<MemberRepositoryModel>>
}