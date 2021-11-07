package com.acm.turi

import com.acm.turi.model.Promocao
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class CursoKotlinApplication {
	companion object {
		val initialPromocoes = arrayOf(
			Promocao(1, "viagem 1", "RS1", true, 71, 2100.99),
			Promocao(2, "viagem 2", "RS2", true, 72, 2200.99),
			Promocao(3, "viagem 3", "RS3", true, 73, 2300.99),
			Promocao(4, "viagem 4", "RS4", true, 74, 2400.99),
			Promocao(5, "viagem 5", "RS5", true, 75, 2500.99),
		)
	}
	@Bean
	fun promocoes() = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))
}

fun main(args: Array<String>) {
	runApplication<CursoKotlinApplication>(*args)
}
