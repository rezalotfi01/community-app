package com.reza.presentation.model

data class MemberUiModel(
    val firstName: String,
    val learns: List<String>,
    val natives: List<String>,
    val pictureUrl: String,
    val referenceCnt: Int,
    val topic: String,
    var isLiked: Boolean
) {
    /**
     * Because we don't have ID in the Api and Models,
     * we use this field to compare two instances of this class.
     */
    val uniqueField by lazy {
        (firstName + learns.toString()
                + natives.toString() + pictureUrl
                + referenceCnt.toString() + topic)
            .replace(" ","")
    }
}
