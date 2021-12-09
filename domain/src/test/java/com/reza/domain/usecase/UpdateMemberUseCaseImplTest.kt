package com.reza.domain.usecase

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.reza.domain.CommunityRepository
import com.reza.domain.model.MemberDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UpdateMemberUseCaseImplTest {
    private lateinit var useCase: UpdateMemberUseCaseImpl

    @Mock
    lateinit var communityRepository: CommunityRepository


    @Before
    fun setUp() {
        useCase = UpdateMemberUseCaseImpl(communityRepository)
    }

    @Test
    fun `When execute then runs update successfully`() {
        runBlocking {
            val member = MemberDomainModel(
                firstName = "Jenie",
                learns = listOf("ja", "es", "it"),
                natives = listOf("en", "pt"),
                pictureUrl = "https://tandem2019.web.app/img/pic1.png",
                referenceCnt = 0,
                topic = "What's something not many people know about you?",
                false
            )

            whenever(communityRepository.updateMember(member)).thenReturn(Unit)

            val actualValue = useCase.execute(member)

            verify(communityRepository, times(1)).updateMember(member)
            assertEquals(Unit, actualValue)
        }
    }
}