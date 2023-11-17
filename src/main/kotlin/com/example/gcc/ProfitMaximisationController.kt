package com.example.gcc

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfitMaximisationController(private val profitMaximisationService: ProfitMaximisationService) {

    @RequestMapping("/")
    fun index() = "This is home!"

    @PostMapping("/profit-maximization", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun postProfitMaximise(@RequestBody inputs: BuySellInputs): BuySellAnswer = profitMaximisationService.postProfitMaximise(inputs)
}


data class BuySellInputs(
        val inputs: List<String>)

data class BuySellAnswer(val answer: List<Int>)
