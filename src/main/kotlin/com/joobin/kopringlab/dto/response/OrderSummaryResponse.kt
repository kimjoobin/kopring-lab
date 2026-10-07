package com.joobin.kopringlab.dto.response

import com.joobin.kopringlab.dto.order.OrderItemResponse

data class OrderSummaryResponse(
    val orderCount: Int,
    val totalAmount: Long,
    val items: List<OrderItemResponse>
)
