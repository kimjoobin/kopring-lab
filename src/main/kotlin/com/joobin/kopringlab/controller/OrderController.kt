package com.joobin.kopringlab.controller

import com.joobin.kopringlab.dto.order.Order
import com.joobin.kopringlab.dto.order.OrderItemResponse
import com.joobin.kopringlab.dto.order.OrderStatus
import com.joobin.kopringlab.dto.reqeust.OrderSummaryRequest
import com.joobin.kopringlab.dto.response.OrderSummaryResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// week02
@RestController
@RequestMapping("/practice/orders")
class OrderController {

    @PostMapping("/summary")
    fun summarize(
        @RequestBody request: OrderSummaryRequest,
    ): OrderSummaryResponse {
        return summarizeOrders(request.orders)
    }

}

fun summarizeOrders(orders: List<Order>): OrderSummaryResponse {
    // 1. PAID인 주문을 고른다.
    // 2. 각 주문을 OrderItemResponse로 변환한다.
    // 3. 변환한 목록으로 건수와 총금액을 계산한다.
    // 4. OrderSummaryResponse를 반환한다.

    val paidOrders = orders.filter { order -> order.status == OrderStatus.PAID }

    val items = paidOrders.map { order ->
        OrderItemResponse(
            order.id,
            order.productName,
            order.unitPrice * order.quantity)
    }

    val totalAmount = items.sumOf { item -> item.amount }

    return OrderSummaryResponse(
        orderCount = items.size,
        totalAmount = totalAmount,
        items = items
    )
}