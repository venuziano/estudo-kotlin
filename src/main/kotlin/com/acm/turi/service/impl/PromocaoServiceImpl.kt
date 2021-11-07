package com.acm.turi.service.impl

import com.acm.turi.model.Promocao
import com.acm.turi.service.PromocaoService
import org.springframework.stereotype.Controller
import java.util.concurrent.ConcurrentHashMap

@Controller
class PromocaoServiceImpl: PromocaoService {
    companion object {
        val initialPromocoes = arrayOf(
            Promocao(1, "viagem 1", "RS1", true, 71, 2100.99),
            Promocao(2, "viagem 2", "RS2", true, 72, 2200.99),
            Promocao(3, "viagem 3", "RS3", true, 73, 2300.99),
            Promocao(4, "viagem 4", "RS4", true, 74, 2400.99),
            Promocao(5, "viagem 5", "RS5", true, 75, 2500.99),
        )
    }
    var promocoes = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))

    override fun create(promocao: Promocao) {
        promocoes[promocao.id] = promocao
    }

    override fun getById(id: Long): Promocao? {
        return promocoes[id]
    }

    override fun delete(id: Long) {
        promocoes.remove(id)
    }

    override fun update(id: Long, promocao: Promocao) {
        delete(id)
        create(promocao)
    }

    override fun searchByLocal(local: String): List<Promocao> =
        promocoes.filter{it.value.local.contains(local, true)}.map(Map.Entry<Long, Promocao>::value).toList()
}