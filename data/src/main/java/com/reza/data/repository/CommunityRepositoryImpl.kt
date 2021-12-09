package com.reza.data.repository

import com.reza.data.CommunityLocalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.reza.data.CommunityRemoteSource
import com.reza.data.mapper.MembersRepoDomainModelMapper
import com.reza.domain.CommunityRepository
import com.reza.domain.model.MemberDomainModel
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val communityRemoteSource: CommunityRemoteSource,
    private val communityLocalSource: CommunityLocalSource,
    private val membersMapper: MembersRepoDomainModelMapper
) : CommunityRepository {

    override suspend fun getMembers(page: Int): Flow<List<MemberDomainModel>> =
        communityRemoteSource.getAllMembers(page)
            .map { membersRepoModelsList ->
                /**
                 * used saved Members list because we can have access to the local saved metadata
                 * about received items (i.e. isLiked)
                 */
                val savedMembersList = communityLocalSource.saveMembersIfNotExists(membersRepoModelsList)

                return@map membersMapper.toDomainModel(savedMembersList)
            }

    override suspend fun updateMember(member: MemberDomainModel) {
        communityLocalSource.updateSavedMember(membersMapper.toRepoModel(member))
    }
}
