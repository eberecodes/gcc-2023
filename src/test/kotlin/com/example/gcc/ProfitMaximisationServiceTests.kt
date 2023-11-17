package com.example.gcc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProfitMaximisationServiceTests {

    @Test
    fun `get the max profit from example post input`() {
        val testInput = BuySellInputs(listOf("14 5 1 6 3 2 5 6 1 3 6 2 5 5 10", "8 100 10 12 5 6 14 5 6"))
        val actual = ProfitMaximisationService().postProfitMaximise(testInput)
        Assertions.assertEquals(BuySellAnswer(listOf(9, 9)), actual)
    }

    @Test
    fun `find max positive difference`() {
        val testInput = "14 5 1 6 3 2 5 6 1 3 6 2 5 5 10"
        val testInput2 = "8 100 10 12 5 6 14 5 6"

        val actual = ProfitMaximisationService().findMaxPositiveDifference(testInput)
        val actual2 = ProfitMaximisationService().findMaxPositiveDifference(testInput2)

        Assertions.assertEquals(9, actual)
        Assertions.assertEquals(9, actual2)
    }
}