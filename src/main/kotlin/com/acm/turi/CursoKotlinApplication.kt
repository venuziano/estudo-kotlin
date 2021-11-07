package com.acm.turi

import com.acm.turi.model.Promocao
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class CursoKotlinApplication

fun main(args: Array<String>) {
	runApplication<CursoKotlinApplication>(*args)
}
