package com.reza.domain.model

data class MemberDomainModel(
    val firstName: String,
    val learns: List<String>,
    val natives: List<String>,
    val pictureUrl: String,
    val referenceCnt: Int,
    val topic: String,
    val isLiked: Boolean
)