package com.reza.remote.mapper

import com.reza.data.model.MemberRepositoryModel
import com.reza.remote.model.MemberResponse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class MemberResponseToRepoMapperImplTest(
    private val givenMembers: List<MemberResponse>,
    private val expected: List<MemberRepositoryModel>
) {

    private lateinit var mapperImpl: MembersResponseToRepoMapperImpl



    @Before
    fun setUp() {
        mapperImpl = MembersResponseToRepoMapperImpl()
    }

    @Test
    fun `Given memberResponseModels when toRepositoryModel then returns expected result`() {
        // When
        val actualValue = mapperImpl.toRepositoryModel(givenMembers)

        // Then
        assertEquals(expected, actualValue)
    }
}
