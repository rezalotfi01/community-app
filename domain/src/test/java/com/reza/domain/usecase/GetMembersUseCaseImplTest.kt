package com.reza.domain.usecase

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.reza.domain.CommunityRepository
import com.reza.domain.model.MemberDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMembersUseCaseImplTest {
    private lateinit var useCase: GetMembersUseCaseImpl

    @Mock
    lateinit var communityRepository: CommunityRepository


    @Before
    fun setUp() {
        useCase = GetMembersUseCaseImpl(communityRepository)
    }

    @ObsoleteCoroutinesApi



    @Test
    fun `When execute then returns expected memberUiModels`() {
        runBlocking {
            // Given
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
            val membersChannel = ConflatedBroadcastChannel<List<MemberDomainModel>>()
            membersChannel.trySend(memberDomainModels).isSuccess
            val expected = listOf(
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

            whenever(communityRepository.getMembers(1)).thenReturn(membersChannel.asFlow())

            // When
            val actualValue = useCase.execute(1).first()

            // Then
            verify(communityRepository, times(1)).getMembers(1)
            assertEquals(expected, actualValue)
        }
    }
}
