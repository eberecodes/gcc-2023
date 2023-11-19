package com.example.gcc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChallengeServiceTests {

    @Test
    fun `get the max profit from example post input`() {
        val testInput = Inputs(listOf("14 5 1 6 3 2 5 6 1 3 6 2 5 5 10", "8 100 10 12 5 6 14 5 6"))
        val actual = ChallengeService().postProfitMaximise(testInput)
        Assertions.assertEquals(AnswerNumeric(listOf(9, 9)), actual)
    }

    @Test
    fun `find max positive difference`() {
        val testInput = "14 5 1 6 3 2 5 6 1 3 6 2 5 5 10"
        val testInput2 = "8 100 10 12 5 6 14 5 6"

        val actual = ChallengeService().findMaxPositiveDifference(testInput)
        val actual2 = ChallengeService().findMaxPositiveDifference(testInput2)

        Assertions.assertEquals(9, actual)
        Assertions.assertEquals(9, actual2)
    }

    @Test
    fun `find max positive difference, when max is not at obvious index`() {
        val testInput = "20 57 77 185 68 118 89 64 77 118 38 188 100 127 199 161 48 112 159 57 144"

        val actual = ChallengeService().findMaxPositiveDifference(testInput)

        Assertions.assertEquals(161, actual)
    }

    @Test
    fun `get the new text encrypted for 1 input text`() {
        val testInput = "coding"
        val actual = ChallengeService().getEncrypted(testInput)
        Assertions.assertEquals("ci on dg", actual)
    }
}