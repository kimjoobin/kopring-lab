package com.joobin.kopringlab.dto.reqeust

import com.joobin.kopringlab.dto.order.Order

data class OrderSummaryRequest(
    val orders: List<Order>
)
