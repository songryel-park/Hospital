package org.example.animalhospital.service

import org.example.animalhospital.entity.PayOrder
import org.example.animalhospital.entity.dto.CreateOrderResponse
import org.example.animalhospital.entity.dto.PayAppContext
import org.example.animalhospital.entity.dto.PayPalDto
import org.example.animalhospital.entity.enums.OrderPage
import org.example.animalhospital.paypal.PayPalHttpClient
import org.example.animalhospital.repository.PayOrderRepository
import org.springframework.stereotype.Service

@Service
class CheckoutService(
    private val paypalHttpClient: PayPalHttpClient,
    private val orderRepository: PayOrderRepository
) {
    fun checkout(order: PayPalDto): CreateOrderResponse {
        val appContext = PayAppContext().apply {
            returnUrl = "http://localhost:8083/checkout/success"
            brandName = "PetHospital"
            landingPage = OrderPage.BILLING
        }
        order.applicationContext = appContext

        val orderResponse = paypalHttpClient.createOrder(order)

        val pay = PayOrder().apply {
            paypalOrderId = orderResponse.paypalOrderId
            orderStatus = orderResponse.status
        }
        orderRepository.save(pay)
        return orderResponse
    }
}