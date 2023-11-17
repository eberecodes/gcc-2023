package com.example.gcc

import org.springframework.stereotype.Service

@Service
class ProfitMaximisationService {
    fun postProfitMaximise(inputs: BuySellInputs): BuySellAnswer {
        return BuySellAnswer(answer = inputs.inputs.map { findMaxPositiveDifference(it) })
    }

    fun findMaxPositiveDifference(input: String): Int {
        val inputSplitToInt = input.split(" ").map { it.toInt() }
        val inputWithoutExpectedDays = inputSplitToInt.drop(0)

        val inputsMap = inputWithoutExpectedDays.mapIndexed { index, i -> index to i }.toMap()
        val sortedInputsMap = inputsMap.toList().sortedBy { (_, value) -> value }.toMap()

        return findMaxDifference(sortedInputsMap.entries.first().toPair(), sortedInputsMap.entries.last().toPair(), sortedInputsMap.entries, sortedInputsMap.size - 1)
    }

    fun findMaxDifference(minPair: Pair<Int, Int>, maxPair: Pair<Int, Int>, entries: Set<Map.Entry<Int, Int>>, size: Int): Int {
        return if (maxPair.first < minPair.first) {
            findMaxDifference(minPair, entries.elementAt(size - 1).toPair(), entries, size - 1)
        } else {
            maxPair.second - minPair.second
        }
    }

}