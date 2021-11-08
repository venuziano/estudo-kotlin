package com.acme.turi.advice

import com.acm.turi.model.ErrorMessage
import com.acm.turi.exception.PromocaoNotFoundException
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)
    fun JsonParseExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse,
                                  exception: Exception):
            ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage("JSON ERROR", exception.message ?: "invalid json" ), HttpStatus.BAD_REQUEST)
    }
    @ExceptionHandler(PromocaoNotFoundException::class)
    fun PromocaoNotFoundExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse,
                                         exception: Exception):ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage("Promocao Nao Localizada", exception.message !!),HttpStatus.NOT_FOUND)

    }
}