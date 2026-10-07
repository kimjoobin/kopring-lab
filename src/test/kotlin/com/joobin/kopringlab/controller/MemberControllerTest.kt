package com.joobin.kopringlab.controller

import com.joobin.kopringlab.global.handler.MemberErrorHandler
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class MemberControllerTest {
    private val mvc = MockMvcBuilders
        .standaloneSetup(MemberController())
        .setControllerAdvice(MemberErrorHandler())
        .build()

    @Test
    fun `이메일과 닉네임의 앞뒤 공백을 제거한다`() {
        mvc.perform(
            post("/members/preview")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """{"email":" joobin@example.com ","nickname":" 주빈 "}"""
                )
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.email").value("joobin@example.com"))
            .andExpect(jsonPath("$.nickname").value("주빈"))
    }

    @Test
    fun `닉네임이 없거나 비어 있으면 이메일 앞부분을 사용한다`() {
        val bodies = listOf(
            """{"email":"joobin@example.com"}""",
            """{"email":"joobin@example.com","nickname":null}""",
            """{"email":"joobin@example.com","nickname":""}""",
            """{"email":"joobin@example.com","nickname":"   "}""",
        )

        bodies.forEach { body ->
            mvc.perform(
                post("/members/preview")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body)
            )
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.nickname").value("joobin"))
        }
    }

    @Test
    fun `이메일이 없거나 잘못되면 400을 반환한다`() {
        val cases = listOf(
            "{}" to "email is required",
            """{"email":null}""" to "email is required",
            """{"email":""}""" to "email is required",
            """{"email":"   "}""" to "email is required",
            """{"email":"joobin"}""" to "email format is invalid",
            """{"email":"@example.com"}""" to "email format is invalid",
            """{"email":"joobin@"}""" to "email format is invalid",
            """{"email":"a@@b"}""" to "email format is invalid",
            """{"email":"joo bin@example.com"}""" to "email format is invalid",
        )

        cases.forEach { (body, message) ->
            mvc.perform(
                post("/members/preview")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body)
            )
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$.message").value(message))
        }
    }
}