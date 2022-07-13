package com.albatros.diraserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DiraserverApplication

fun main(args: Array<String>) {
	runApplication<DiraserverApplication>(*args)
}
