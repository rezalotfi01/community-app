package com.reza.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.reza.domain.model.MemberDomainModel
import com.reza.domain.usecase.GetMembersUseCase
import com.reza.domain.usecase.UpdateMemberUseCase
import com.reza.presentation.mapper.MembersDomainUiModelMapper
import com.reza.presentation.model.MemberUiModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MembersViewModelTest {
    private lateinit var viewModel: MembersViewModel

    @Mock
    lateinit var getMembersUseCase: GetMembersUseCase

    @Mock
    lateinit var updateMembersUseCase: UpdateMemberUseCase


    @Mock
    lateinit var membersMapper: MembersDomainUiModelMapper

    @Before
    fun setUp() {
        val testDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testDispatcher)
        viewModel = MembersViewModel(getMembersUseCase, updateMembersUseCase, membersMapper)
    }

    @Test
    fun `When members then memberUiModelRetrieved with expected result`() {

        viewModel.viewModelScope.launch {

            // Given
            val memberUiModelRetrievedTestObserver = Observer<PagingData<MemberUiModel>> { }
            viewModel.membersStreamLiveData.observe(mock(),memberUiModelRetrievedTestObserver)
            val memberDomainModels = listOf(
                MemberDomainModel(
                    firstName = "Arnauld",
                    learns = listOf("en", "ko"),
                    natives = listOf("de", "es", "it"),
                    pictureUrl = "https://tandem2019.web.app/img/pic1.png",
                    referenceCnt = 0,
                    topic = "What's something not many people know about you?",
                    false
                ),
                MemberDomainModel(
                    firstName = "Jenie",
                    learns = listOf("ja", "es","it"),
                    natives = listOf("en", "pt"),
                    pictureUrl = "https://tandem2019.web.app/img/pic1.png",
                    referenceCnt = 0,
                    topic = "What's something not many people know about you?",
                    false
                )
            )
            val expected = listOf(
                MemberUiModel(
                    firstName = "Arnauld",
                    learns = listOf("en", "ko"),
                    natives = listOf("de", "es", "it"),
                    pictureUrl = "https://tandem2019.web.app/img/pic1.png",
                    referenceCnt = 0,
                    topic = "What's something not many people know about you?",
                    false
                ),
                MemberUiModel(
                    firstName = "Jenie",
                    learns = listOf("ja", "es","it"),
                    natives = listOf("en", "pt"),
                    pictureUrl = "https://tandem2019.web.app/img/pic1.png",
                    referenceCnt = 0,
                    topic = "What's something not many people know about you?",
                    false
                )
            )
            val flow = flow {
                emit(memberDomainModels)
            }
            whenever(getMembersUseCase.execute(1)).thenReturn(flow)
            whenever(membersMapper.toUiModel(memberDomainModels)).thenReturn(expected)

            // When
            viewModel.getMembers()
            val actualValue = viewModel.membersStreamLiveData.value

            // Then
            verify(getMembersUseCase, times(1)).execute(1)

            assertEquals(expected, actualValue)
        }
    }



}
