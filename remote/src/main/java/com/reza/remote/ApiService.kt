package com.reza.remote

import com.reza.remote.model.MemberResponse
import com.reza.remote.model.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/community_{page}.json")
    suspend fun getMembers(@Path("page") page: Int): ResponseWrapper<MemberResponse>
}
