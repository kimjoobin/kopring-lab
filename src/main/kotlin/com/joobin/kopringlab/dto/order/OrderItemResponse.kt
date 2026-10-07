package com.joobin.kopringlab.dto.order

data class OrderItemResponse(
    val id: Long,
    val productName: String,
    val amount: Long,
)
