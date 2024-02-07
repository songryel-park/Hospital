package org.example.animalhospital.entity.dto

import org.example.animalhospital.payment.PaymentInfo

data class PaymentRequest(
    var reserveId: Long,
    var paymentInfo: PaymentInfo,
    var walletId: Long
)