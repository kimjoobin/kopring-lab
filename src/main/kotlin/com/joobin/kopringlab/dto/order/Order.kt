package com.joobin.kopringlab.dto.order

data class Order(
    val id: Long,
    val productName: String,
    val unitPrice: Long,
    val quantity: Int,
    val status: OrderStatus
) /*{  이건 멤버함수
    fun toResponse(): OrderItemResponse {
        return OrderItemResponse(
            id = id,
            productName = productName,
            amount = unitPrice * quantity,
        )
    }
}*/

// 확장함수
// kotlin 공식 가이드는 객체 중심으로 동작하는 함수에 확장 함수를 적극적으로 고려하라고 권장함
fun Order.toResponse(): OrderItemResponse {
    return OrderItemResponse(
        id = this.id,
        productName = this.productName,
        amount = this.unitPrice * this.quantity,
    )
}
