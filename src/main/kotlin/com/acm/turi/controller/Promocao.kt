package com.acm.turi.controller

import com.acm.turi.model.Promocao
import com.acm.turi.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping(value = ["/promocoes"])
class PromocaoController {
    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping(value = ["/{id}"])
    fun getByID(@PathVariable id: Long) = promocaoService.getById(id)

    @PostMapping()
    fun create(@RequestBody promocao: Promocao) = promocaoService.create(promocao)

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: Long) = promocaoService.delete(id)

    @PutMapping(value = ["/{id}"])
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao) = promocaoService.update(id, promocao)

    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "") localFilter: String) = promocaoService.searchByLocal(localFilter)
}