package com.example.gcc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GccApplication

fun main(args: Array<String>) {
	runApplication<GccApplication>(*args)
}
