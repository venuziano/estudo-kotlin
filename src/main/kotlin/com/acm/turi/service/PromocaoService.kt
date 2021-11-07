package com.acm.turi.service

import com.acm.turi.model.Promocao

interface PromocaoService {
    fun create(promocao: Promocao)
    fun getById(id: Long): Promocao?
    fun delete(id: Long)
    fun update(id: Long, promocao:Promocao)
    fun searchByLocal(local: String): List<Promocao>
}