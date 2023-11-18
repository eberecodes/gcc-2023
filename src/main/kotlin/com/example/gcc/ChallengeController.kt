package com.example.gcc

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChallengeController(private val challengeService: ChallengeService) {

    @RequestMapping("/")
    fun index() = "This is home!"

    @PostMapping("/profit-maximization", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun postProfitMaximise(@RequestBody inputs: Inputs): AnswerNumeric = challengeService.postProfitMaximise(inputs)

    @PostMapping("/data-encryption", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun postDataEncryption(@RequestBody inputs: Inputs): Answer = challengeService.postDataEncryption(inputs)
}


data class Inputs(
        val inputs: List<String>)

data class AnswerNumeric(val answer: List<Int>)
data class Answer(val answer: List<String>)
