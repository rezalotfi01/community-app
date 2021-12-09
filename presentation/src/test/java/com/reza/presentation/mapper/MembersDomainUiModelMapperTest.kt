package com.reza.presentation.mapper

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.MethodRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import com.reza.domain.model.MemberDomainModel
import com.reza.presentation.model.MemberUiModel
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class MembersDomainUiModelMapperTest {

    private lateinit var membersDomainUiModelMapperImpl: MembersDomainUiModelMapperImpl

    @get:Rule
    var rule: MethodRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        membersDomainUiModelMapperImpl = MembersDomainUiModelMapperImpl()
    }

    @Test
    fun `Given 2 members when toUiModel then return expected result`() {
        // Given
        val members = listOf(
            MemberDomainModel(
                firstName = "Jenie",
                learns = listOf("ja", "es", "it"),
                natives = listOf("en", "pt"),
                pictureUrl = "https://tandem2019.web.app/img/pic1.png",
                referenceCnt = 0,
                topic = "What's something not many people know about you?",
                false
            ),
            MemberDomainModel(
                firstName = "Arnauld",
                learns = listOf("en", "ko"),
                natives = listOf("de", "es", "it"),
                pictureUrl = "https://tandem2019.web.app/img/pic1.png",
                referenceCnt = 0,
                topic = "What's something not many people know about you?",
                false
            )
        )

        val expected = listOf(
            MemberUiModel(
                firstName = "Jenie",
                learns = listOf("ja", "es", "it"),
                natives = listOf("en", "pt"),
                pictureUrl = "https://tandem2019.web.app/img/pic1.png",
                referenceCnt = 0,
                topic = "What's something not many people know about you?",
                false
            ),
            MemberUiModel(
                firstName = "Arnauld",
                learns = listOf("en", "ko"),
                natives = listOf("de", "es", "it"),
                pictureUrl = "https://tandem2019.web.app/img/pic1.png",
                referenceCnt = 0,
                topic = "What's something not many people know about you?",
                false
            )
        )


        // When
        val actualValue = membersDomainUiModelMapperImpl.toUiModel(members)

        // Then
        assertEquals(expected, actualValue)

    }

    @Test
    fun `Given member when toDomainModel then return expected result`() {
        // Given
        val member = MemberUiModel(
                firstName = "Arnauld",
                learns = listOf("en", "ko"),
                natives = listOf("de", "es", "it"),
                pictureUrl = "https://tandem2019.web.app/img/pic1.png",
                referenceCnt = 0,
                topic = "What's something not many people know about you?",
                false
            )


        val expected =  MemberDomainModel(
            firstName = "Jenie",
            learns = listOf("ja", "es", "it"),
            natives = listOf("en", "pt"),
            pictureUrl = "https://tandem2019.web.app/img/pic1.png",
            referenceCnt = 0,
            topic = "What's something not many people know about you?",
            false
        )


        // When
        val actualValue = membersDomainUiModelMapperImpl.toDomainModel(member)

        // Then
        assertEquals(expected, actualValue)
    }

    @Test
    fun `Given no members when toUiModel then return expected result`() {
        // Given
        val members = emptyList<MemberDomainModel>()
        val expected = emptyList<MemberUiModel>()

        // When
        val actualValue = membersDomainUiModelMapperImpl.toUiModel(members)

        // Then
        assertEquals(expected, actualValue)
    }
}
