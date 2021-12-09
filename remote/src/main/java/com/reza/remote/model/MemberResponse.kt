package com.reza.remote.model

data class MemberResponse(
    val firstName: String,
    val learns: List<String>,
    val natives: List<String>,
    val pictureUrl: String?,
    val referenceCnt: Int,
    val topic: String
)
