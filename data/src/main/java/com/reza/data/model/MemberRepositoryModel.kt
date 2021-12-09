package com.reza.data.model

data class MemberRepositoryModel(
    val firstName: String,
    val learns: List<String>,
    val natives: List<String>,
    val pictureUrl: String,
    val referenceCnt: Int,
    val topic: String,
    val isLiked: Boolean
)

