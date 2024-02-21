package org.example.animalhospital.controller

import lombok.RequiredArgsConstructor
import org.example.animalhospital.entity.dto.BillingRequest
import org.example.animalhospital.entity.dto.PaymentRequest
import org.example.animalhospital.exception.BaseException
import org.example.animalhospital.exception.Response
import org.example.animalhospital.service.PaymentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
class PaymentController(private val paymentService: PaymentService) {
    @PostMapping("/payment")
    fun payment(@RequestBody request: PaymentRequest): ResponseEntity<String> {
        paymentService.payment(request)
        return ResponseEntity("결제가 완료되었습니다.", HttpStatus.OK)
    }

    @PostMapping("/payment")
    fun refund(@PathVariable paymentId: String, @RequestBody request: PaymentRequest): ResponseEntity<String> {
        paymentService.refund(request)
        return ResponseEntity("환불처리 되었습니다.", HttpStatus.OK)
    }

    @PostMapping("/verify/{imp_uid}")
    fun issueBilling(@PathVariable("imp_uid") impUid: String, @RequestBody request: BillingRequest): Response<Any> {
        try {
            val issueBilling = paymentService.issueBilling(request, impUid)
            return Response(data = issueBilling)
        } catch (exception: BaseException) {
            return Response(message = exception.message)
        }
    }
}