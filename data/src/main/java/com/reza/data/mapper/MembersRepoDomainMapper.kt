package com.reza.data.mapper

import com.reza.data.model.MemberRepositoryModel
import com.reza.domain.model.MemberDomainModel
import javax.inject.Inject

interface MembersRepoDomainModelMapper {
    fun toDomainModel(membersRepositoryModelList: List<MemberRepositoryModel>): List<MemberDomainModel>
    fun toRepoModel(memberDomainModel: MemberDomainModel): MemberRepositoryModel
}

class MembersRepoDomainModelMapperImpl @Inject constructor() :
    MembersRepoDomainModelMapper {
    override fun toDomainModel(
        membersRepositoryModelList: List<MemberRepositoryModel>
    ): List<MemberDomainModel> =
        membersRepositoryModelList.map {
            MemberDomainModel(
                firstName = it.firstName,
                learns = it.learns,
                natives = it.natives,
                pictureUrl = it.pictureUrl,
                referenceCnt = it.referenceCnt,
                topic = it.topic,
                isLiked = it.isLiked
            )
        }

    override fun toRepoModel(memberDomainModel: MemberDomainModel): MemberRepositoryModel {
        return memberDomainModel.let {
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

}
