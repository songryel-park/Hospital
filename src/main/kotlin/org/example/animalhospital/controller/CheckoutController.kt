package org.example.animalhospital.controller

import jakarta.servlet.http.HttpServletRequest
import lombok.extern.slf4j.Slf4j
import org.example.animalhospital.entity.PayOrder
import org.example.animalhospital.entity.dto.CreateOrderResponse
import org.example.animalhospital.entity.dto.PayAppContext
import org.example.animalhospital.entity.dto.PayPalDto
import org.example.animalhospital.entity.enums.OrderPage
import org.example.animalhospital.entity.enums.PayPalStatus
import org.example.animalhospital.paypal.PayPalHttpClient
import org.example.animalhospital.repository.PayOrderRepository
import org.example.animalhospital.service.CheckoutService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Slf4j
@RequestMapping("/checkout")
@Controller
class CheckoutController(
    private val checkoutService: CheckoutService
) {
    @PostMapping
    fun checkout(@RequestBody order: PayPalDto): ResponseEntity<CreateOrderResponse> {
        val orderResponse = checkoutService.checkout(order)
        return ResponseEntity.ok(orderResponse)
    }
}
