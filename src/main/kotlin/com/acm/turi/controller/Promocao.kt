package com.acm.turi.controller

import com.acm.turi.model.ErrorMessage
import com.acm.turi.model.Promocao
import com.acm.turi.model.RespostaJSON
import com.acm.turi.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import javax.xml.ws.Response

@RestController
@RequestMapping(value = ["/promocoes"])
class PromocaoController {
    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping(value = ["/{id}"])
    fun getByID(@PathVariable id: Long): ResponseEntity<Any> {
        var promocao = this.promocaoService.getById(id)
        return return if(promocao != null)
            ResponseEntity(promocao,HttpStatus.OK)
        else
            ResponseEntity(ErrorMessage("Promocao Nao Localizada","promocao ${id} nao localizada"),HttpStatus.NOT_FOUND)
    }

    @PostMapping()
    fun create(@RequestBody promocao: Promocao): ResponseEntity<RespostaJSON> {
        promocaoService.create(promocao)
        val respostaJSON = RespostaJSON("OK", Date())
        return ResponseEntity(respostaJSON, HttpStatus.CREATED)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if(promocaoService.getById(id) != null) {
            promocaoService.delete(id)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping(value = ["/{id}"])
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (promocaoService.getById(id) != null) {
            promocaoService.update(id, promocao)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "") localFilter: String): ResponseEntity<List<Promocao>> {
        var status = HttpStatus.OK
        var promotionList = promocaoService.searchByLocal(localFilter)
        if(promotionList.size == 0) {
            status = HttpStatus.NOT_FOUND
        }
        return ResponseEntity(promotionList, status)
    }
}