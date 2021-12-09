package com.reza.presentation.mapper

import com.reza.domain.model.MemberDomainModel
import com.reza.presentation.model.MemberUiModel
import javax.inject.Inject

interface MembersDomainUiModelMapper {
    fun toUiModel(
        membersList: List<MemberDomainModel>
    ): List<MemberUiModel>

    fun toDomainModel(
        member: MemberUiModel
    ) : MemberDomainModel
}

class MembersDomainUiModelMapperImpl @Inject constructor() : MembersDomainUiModelMapper {
    override fun toUiModel(
        membersList: List<MemberDomainModel>
    ): List<MemberUiModel> = membersList.map {
        MemberUiModel(
            firstName = it.firstName,
            learns = it.learns,
            natives = it.natives,
            pictureUrl = it.pictureUrl,
            referenceCnt = it.referenceCnt,
            topic = it.topic,
            isLiked = it.isLiked
        )
    }

    override fun toDomainModel(member: MemberUiModel): MemberDomainModel {
        return member.let {
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
    }
}
