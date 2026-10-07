package com.joobin.kopringlab.dto.order

data class Order(
    val id: Long,
    val productName: String,
    val unitPrice: Long,
    val quantity: Int,
    val status: OrderStatus
)
