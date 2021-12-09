package com.reza.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.reza.core.livedata.LiveEvent
import com.reza.domain.usecase.GetMembersUseCase
import com.reza.domain.usecase.UpdateMemberUseCase
import com.reza.presentation.mapper.MembersDomainUiModelMapper
import com.reza.presentation.model.MemberUiModel
import com.reza.presentation.paging.MembersPagingSource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MembersViewModel @Inject constructor(
    private val getMembersUseCase: GetMembersUseCase,
    private val updateMemberUseCase: UpdateMemberUseCase,
    private val membersDomainUiMapper: MembersDomainUiModelMapper
) : ViewModel() {

    private val _membersStreamLiveData = MediatorLiveData<PagingData<MemberUiModel>>()
    val membersStreamLiveData: LiveData<PagingData<MemberUiModel>> get() = _membersStreamLiveData

    /**
     * This liveData has LiveEvent type, so it will trigger only when new value added and
     * Not trigger during configuration change, initializing observer, etc.
     */
    private val _errorEvent = LiveEvent<String>()
    val errorEvent: LiveData<String> get() = _errorEvent

    private val _loadingStateEvent = LiveEvent<Boolean>()
    val loadingEvent: LiveData<Boolean> get() = _loadingStateEvent

    /**
     * Paging and Pager implemented here because the inner layers (i.e. Domain layer) should be
     * pure kotlin and we should use Android Framework tools in the outer layers (Presentation layer)
     */
    fun getMembers() {
        _loadingStateEvent.postValue(true)
        val membersFlow = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MembersPagingSource(
                    getMembersUseCase,
                    membersDomainUiMapper,
                    pageSize = 20
                )
            }
        ).flow
            .cachedIn(viewModelScope)
            .distinctUntilChanged()
            .onEach {
                _loadingStateEvent.postValue(false)
                _membersStreamLiveData.postValue(it)
            }
        try {
            membersFlow.launchIn(viewModelScope)
        }catch (e: Exception){
            handleExceptions(e)
        }

    }

    private fun handleExceptions(throwable: Throwable) {
        Timber.e(throwable)
        _errorEvent.postValue(throwable.localizedMessage ?: "")
        _loadingStateEvent.postValue(false)
    }


    fun memberLiked(model: MemberUiModel){
        setMemberLike(model,true)
    }

    fun memberUnliked(model: MemberUiModel){
        setMemberLike(model,false)
    }

    private fun setMemberLike(model: MemberUiModel, liked: Boolean) = viewModelScope.launch {
        model.isLiked = liked
        updateMemberUseCase.execute(
            membersDomainUiMapper.toDomainModel(model)
        )
    }

}