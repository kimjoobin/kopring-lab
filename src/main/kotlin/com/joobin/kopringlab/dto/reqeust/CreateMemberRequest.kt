package com.joobin.kopringlab.dto.reqeust

import com.joobin.kopringlab.dto.response.CreateMemberCommand
import com.joobin.kopringlab.global.request.InvalidMemberRequest

data class CreateMemberRequest(
    val email: String? = null,
    val nickname: String? = null,
) {
    fun toCommand(): CreateMemberCommand {
        // takeIf { it.isNotBlank() } 는 조건을 만족하면 원래 값을, 아니면 null을 반환한다
        // email?.trim()?.takeIf { it.isNotBlank() } 이렇게 해도 됨
        val normalEmail = email?.trim().takeIf { it?.isNotBlank() == true } ?: throw InvalidMemberRequest("Please enter a valid email")

        val emailRegex = Regex("""^[^\s@]+@[^\s@]+$""")
        if (!emailRegex.matches(normalEmail)) {
            throw InvalidMemberRequest("Please enter a valid email")
        }

        val normalNickname = nickname?.trim().takeIf { it?.isNotBlank() == true }
            ?: normalEmail.substringBefore("@")

        return CreateMemberCommand(normalEmail, normalNickname)
    }
}
