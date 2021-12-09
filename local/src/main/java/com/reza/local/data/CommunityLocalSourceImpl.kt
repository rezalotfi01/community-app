package com.reza.local.data

import com.reza.data.CommunityLocalSource
import com.reza.data.model.MemberRepositoryModel
import com.reza.local.dao.MemberDao
import com.reza.local.mapper.MembersEntityRepoMapper
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import timber.log.Timber

class CommunityLocalSourceImpl(
    private val dao: MemberDao,
    private val memberMapper: MembersEntityRepoMapper
) : CommunityLocalSource {
    /**
     * This method will save received member objects in DB if objects not existed before.
     * We created a list as a result, if object existed in DB it will add the object from DB
     * to result list and if it's not existed in DB, it will insert that object to DB and will add
     * to result list. and finally it returns the list to use and show in the app.
     * In this way, we can access to locally saved metadata (i.e. isLiked field) and can use it.
     */
    override suspend fun saveMembersIfNotExists(members: List<MemberRepositoryModel>)
    : List<MemberRepositoryModel> {
        val result: MutableList<MemberRepositoryModel> = mutableListOf()
        members.forEach { memberRepositoryModel ->
            val memberEntity = memberMapper.toEntityModel(memberRepositoryModel)
            val existedEntity = dao.getMemberByUniqueStrId(memberEntity.uniqueStrId).firstOrNull()
            if (existedEntity == null) {
                dao.insertMember(memberEntity)
                result.add(memberRepositoryModel)
            }else{
                result.add(memberMapper.toRepositoryModel(existedEntity))
            }
        }
        Timber.e("")
        return result
    }

    override suspend fun updateSavedMember(member: MemberRepositoryModel) {
        val updatedMember = memberMapper.toEntityModel(member)
        val memberFromDB = dao.getMemberByUniqueStrId(updatedMember.uniqueStrId).first()
        updatedMember.id = memberFromDB.id
        dao.insertMember(updatedMember)
    }
}