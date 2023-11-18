package com.example.gcc

import org.springframework.stereotype.Service
import kotlin.math.ceil
import kotlin.math.sqrt

@Service
class ChallengeService {
    fun postProfitMaximise(inputs: Inputs): AnswerNumeric {
        return AnswerNumeric(answer = inputs.inputs.map { findMaxPositiveDifference(it) })
    }

    fun findMaxPositiveDifference(input: String): Int {
        val inputSplitToInt = input.split(" ").map { it.toInt() }.toMutableList()
        val inputWithoutExpectedDays = inputSplitToInt.drop(1)

        val inputsMap = inputWithoutExpectedDays.mapIndexed { index, i -> index to i }.toMap()
        val sortedInputsMap = inputsMap.toList().sortedBy { (_, value) -> value }.toMap()

        return findMaxDifference(sortedInputsMap.entries.first().toPair(), sortedInputsMap.entries.last().toPair(), sortedInputsMap.entries, sortedInputsMap.size - 1)
    }

    fun findMaxDifference(minPair: Pair<Int, Int>, maxPair: Pair<Int, Int>, entries: Set<Map.Entry<Int, Int>>, size: Int): Int {
        return if (maxPair.first < minPair.first) {
            findMaxDifference(minPair, entries.elementAt(size - 1).toPair(), entries, size - 1)
        } else {
            println(maxPair)
            println(minPair)
            maxPair.second - minPair.second
        }
    }

    fun postDataEncryption(inputs: Inputs): Answer {
        return Answer(answer = inputs.inputs.map { getEncrypted(it) })
    }

    fun getEncrypted(testInput: String): String {
        val stringSplitWithoutSpace = testInput.split(" ")
        var stringWithoutSpace = ""
        stringSplitWithoutSpace.forEach { stringWithoutSpace += it }
        val stringLength = stringWithoutSpace.length
        val root = sqrt(stringLength.toDouble())
        val cols = kotlin.math.floor(root).toInt()
        val rows = ceil(root).toInt() //rename rows to cols
        val startingLetter = stringWithoutSpace[0].toString()
        stringWithoutSpace = stringWithoutSpace.removeRange(0, 1)

        return textToAdd(stringWithoutSpace, rows, rows - 1, startingLetter, stringLength).chunked(cols).joinToString(" ")
    }

    fun textToAdd(testInput: String, rows: Int, index: Int, currentString: String, stringLength: Int): String {
        return if (currentString.length == stringLength) {
            currentString
        } else {
            var encrypted = ""
            var newIndex = index
            var filteredInput = ""
            try {
                encrypted = testInput[newIndex].toString()
                filteredInput = testInput.removeRange(newIndex, newIndex + 1)
            } catch (e: Exception) {
                newIndex = index - testInput.length
                while (newIndex + 1 > testInput.length) {
                    newIndex -= testInput.length
                }
                encrypted = testInput[newIndex].toString()
                try {
                    filteredInput = testInput.removeRange(newIndex, newIndex + 1)
                } catch (e: Exception) {
                    return currentString + encrypted
                }
            }
            textToAdd(filteredInput, rows, rows + 1, currentString + encrypted, stringLength)
        }
    }


}