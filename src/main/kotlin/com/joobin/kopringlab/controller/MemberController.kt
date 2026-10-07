package com.joobin.kopringlab.controller

import com.joobin.kopringlab.dto.reqeust.CreateMemberRequest
import com.joobin.kopringlab.dto.response.CreateMemberCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// week01
@RestController
@RequestMapping("/members")
class MemberController {

    @PostMapping("/preview")
    fun preview(
        @RequestBody request: CreateMemberRequest
    ): CreateMemberCommand = request.toCommand()
}