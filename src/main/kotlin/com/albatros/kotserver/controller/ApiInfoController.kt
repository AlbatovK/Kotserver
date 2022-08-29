package com.albatros.kotserver.controller

import com.albatros.kotserver.domain.AppConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/info")
class ApiInfoController @Autowired constructor(var config: AppConfig) {

    @GetMapping("/api")
    fun getApiInfo() = config

}