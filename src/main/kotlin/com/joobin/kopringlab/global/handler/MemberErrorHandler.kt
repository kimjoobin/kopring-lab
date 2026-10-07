package com.joobin.kopringlab.global.handler

import com.joobin.kopringlab.global.request.InvalidMemberRequest
import com.joobin.kopringlab.global.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MemberErrorHandler {

    @ExceptionHandler(InvalidMemberRequest::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(ex: InvalidMemberRequest): ErrorResponse =
        ErrorResponse(ex.message ?: "invalid request")
}