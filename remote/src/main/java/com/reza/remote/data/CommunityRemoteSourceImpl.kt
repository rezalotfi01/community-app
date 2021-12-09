package com.reza.remote.data

import com.reza.data.CommunityRemoteSource
import com.reza.data.model.MemberRepositoryModel
import com.reza.remote.ApiService
import com.reza.remote.mapper.MembersResponseToRepoMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CommunityRemoteSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val membersMapper: MembersResponseToRepoMapper
) : CommunityRemoteSource {

    private val _membersSharedFlow = MutableStateFlow(getInitialStateModels())
    private val membersSharedFlow = _membersSharedFlow.asSharedFlow()

    override suspend fun getAllMembers(page: Int): Flow<List<MemberRepositoryModel>> {

        val response = apiService.getMembers(page)

        if (response.errorCode != null)
            Timber.e("Error in connecting to the server, error code: ${response.errorCode}")

        response.data?.let { data ->
            _membersSharedFlow.emit(
                membersMapper.toRepositoryModel(data)
            )
        }

        return membersSharedFlow.distinctUntilChanged()
    }

    private fun getInitialStateModels() = listOf(
        MemberRepositoryModel("", listOf(), listOf(),"",-1,"",false)
    )
}
