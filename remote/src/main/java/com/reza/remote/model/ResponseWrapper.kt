package com.reza.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T>(
    val errorCode: Any? = null,
    @SerializedName("response")
    val data: List<T>? = null,
    val type: String
)