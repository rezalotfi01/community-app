package com.reza.local.mapper

import com.reza.data.model.MemberRepositoryModel
import com.reza.local.model.MemberEntity
import dagger.Reusable
import javax.inject.Inject

interface MembersEntityRepoMapper {
    fun toRepositoryModel(membersEntitiesList: List<MemberEntity>): List<MemberRepositoryModel>
    fun toRepositoryModel(membersEntity: MemberEntity): MemberRepositoryModel
    fun toEntityModel(memberRepositoryModel: MemberRepositoryModel): MemberEntity
}

@Reusable
class MembersEntityRepoMapperImpl @Inject constructor() : MembersEntityRepoMapper {

    override fun toRepositoryModel(
        membersEntitiesList: List<MemberEntity>
    ): List<MemberRepositoryModel> {
        return membersEntitiesList.map {
            MemberRepositoryModel(
                firstName = it.firstName,
                learns = it.learns,
                natives = it.natives,
                pictureUrl = it.pictureUrl,
                referenceCnt = it.referenceCnt,
                topic = it.topic,
                isLiked = it.isLiked
            )
        }
    }

    override fun toRepositoryModel(membersEntity: MemberEntity): MemberRepositoryModel {
        return membersEntity.let {
            MemberRepositoryModel(
                firstName = it.firstName,
                learns = it.learns,
                natives = it.natives,
                pictureUrl = it.pictureUrl,
                referenceCnt = it.referenceCnt,
                topic = it.topic,
                isLiked = it.isLiked,
            )
        }
    }

    override fun toEntityModel(memberRepositoryModel: MemberRepositoryModel): MemberEntity {
        return memberRepositoryModel.let {
            MemberEntity(
                id = 0,
                firstName = it.firstName,
                learns = it.learns,
                natives = it.natives,
                pictureUrl = it.pictureUrl,
                referenceCnt = it.referenceCnt,
                topic = it.topic,
                isLiked = it.isLiked,
                /**
                 * Because we don't have ID in the Api,
                 * we use this field to compare two instances of this class.
                 */
                uniqueStrId = it.firstName + it.learns.toString() + it.natives + it.pictureUrl + it.referenceCnt + it.topic
            )
        }
    }
}