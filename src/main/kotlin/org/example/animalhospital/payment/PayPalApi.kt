package org.example.animalhospital.payment

import org.example.animalhospital.exception.PaymentResult
import org.springframework.stereotype.Service

@Service
interface PayPalApi {
    fun creditPayment(paymentInfo: PaymentInfo): PaymentResult
    fun accountPayment(paymentInfo: PaymentInfo): PaymentResult
}
