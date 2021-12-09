package com.reza.remote.mapper

import com.reza.data.model.MemberRepositoryModel
import com.reza.remote.model.MemberResponse
import dagger.Reusable
import javax.inject.Inject

interface MembersResponseToRepoMapper {
    fun toRepositoryModel(membersResponseList: List<MemberResponse>): List<MemberRepositoryModel>
}

const val DEFAULT_PICTURE = "https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-256.png"

@Reusable
class MembersResponseToRepoMapperImpl @Inject constructor() : MembersResponseToRepoMapper {

    override fun toRepositoryModel(
        membersResponseList: List<MemberResponse>
    ): List<MemberRepositoryModel> {
        return membersResponseList.map {
            MemberRepositoryModel(
                firstName = it.firstName,
                learns = it.learns,
                natives = it.natives,
                pictureUrl = if ( !it.pictureUrl.isNullOrEmpty() ) it.pictureUrl else DEFAULT_PICTURE,
                referenceCnt = it.referenceCnt,
                topic = it.topic,
                isLiked = false
            )
        }
    }
}
