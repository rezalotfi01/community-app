package com.reza.data.mapper

import com.reza.data.model.MemberRepositoryModel
import com.reza.domain.model.MemberDomainModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class MembersRepoDomainModelMapperImplTest(
    private val givenMembers: List<MemberRepositoryModel>,
    private val expected: List<MemberDomainModel>
) {

    private lateinit var repoDomainModelMapperImpl: MembersRepoDomainModelMapperImpl

    @Before
    fun setUp() {
        repoDomainModelMapperImpl = MembersRepoDomainModelMapperImpl()
    }

    @Test
    fun `Given memberRepositoryModels when toDomainModel then returns expected result`() {
        // When
        val actualValue = repoDomainModelMapperImpl.toDomainModel(givenMembers)

        // Then
        assertEquals(expected, actualValue)
    }
}
