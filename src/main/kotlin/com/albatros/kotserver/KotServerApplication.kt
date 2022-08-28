package com.albatros.kotserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KotServerApplication

fun main(args: Array<String>) {
	runApplication<KotServerApplication>(*args)
}

@RestController
class Controller {
	@GetMapping("/api")
	fun getApiVersion() = "1.0"
}
