package org.example.animalhospital.controller

import lombok.RequiredArgsConstructor
import org.example.animalhospital.entity.dto.PaymentRequest
import org.example.animalhospital.service.PaymentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
class PaymentController(private val paymentService: PaymentService) {
    @PostMapping("/payment")
    fun payment(@RequestBody request: PaymentRequest): ResponseEntity<String> {
        paymentService.payment(request)
        return ResponseEntity("결제가 완료되었습니다.", HttpStatus.OK)
    }

//    @PostMapping("/payment/{paymentId}")
//    fun refund(@RequestBody request: PaymentRequest) {}
}