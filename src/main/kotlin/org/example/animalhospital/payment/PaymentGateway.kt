package org.example.animalhospital.payment

interface PaymentGateway {
    fun processPayment(paymentInfo: PaymentInfo)
}
