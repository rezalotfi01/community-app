package com.reza.remote.data


import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.reza.core_android_test.MainCoroutineRule
import com.reza.data.CommunityRemoteSource
import com.reza.remote.ApiService
import com.reza.remote.mapper.MembersResponseToRepoMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CommunityRemoteSourceImplTest {
    private lateinit var remoteSource: CommunityRemoteSource

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var membersRepositoryMapper: MembersResponseToRepoMapper

//    @get:Rule
//    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @ExperimentalCoroutinesApi
    @FlowPreview
    @Before
    fun setUp() {
        remoteSource = CommunityRemoteSourceImpl(
            apiService,
            membersRepositoryMapper
        )
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `When getAllMembers then apiService invoked`() {
        runBlockingTest {
            // When
            whenever(apiService.getMembers(1)).thenReturn(mock())

            remoteSource.getAllMembers(1)

            // Then
            verify(apiService, times(1)).getMembers(1)
        }
    }
}
