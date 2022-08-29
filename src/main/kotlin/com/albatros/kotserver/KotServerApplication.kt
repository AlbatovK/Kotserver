package com.albatros.kotserver

import com.albatros.kotserver.domain.AppConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AppConfig::class)
class KotServerApplication

fun main(args: Array<String>) {
    runApplication<KotServerApplication>(*args)
}
