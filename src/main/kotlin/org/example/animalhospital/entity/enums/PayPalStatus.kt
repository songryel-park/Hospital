package org.example.animalhospital.entity.enums

enum class PayPalStatus(val value: String) {
    ORDER("결제생성"),
    SAVE("결제저장"),
    APPROVED("결제승인"),
    COMPLETE("결제완료")
}