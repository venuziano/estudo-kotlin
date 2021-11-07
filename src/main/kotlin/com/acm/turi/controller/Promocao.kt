package com.acm.turi.controller

import com.acm.turi.model.Promocao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping(value = ["/promocoes"])
class PromocaoController {
    @Autowired
    lateinit var promocoes: ConcurrentHashMap<Long, Promocao>

    @GetMapping(value = ["/{id}"])
    fun getByID(@PathVariable id: Long) = promocoes[id]

    @PostMapping()
    fun create(@RequestBody promocao: Promocao) {
        promocoes[promocao.id] = promocao
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: Long) {
        promocoes.remove(id)
    }

    @PutMapping(value = ["/{id}"])
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao) {
        promocoes.remove(id)
        promocoes[id] = promocao
    }

    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "") localFilter: String) =
        promocoes.filter{it.value.local.contains(localFilter, true)}.map(Map.Entry<Long, Promocao>::value).toList()
}