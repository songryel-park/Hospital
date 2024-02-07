package org.example.animalhospital.entity.dto

data class BillingRequest(
    var pw4: String? = null,
    var cardNumber: String? = null, // 카드 번호는 카멜케이스로 변경
    var expiry: String? = null,
    var birth: String? = null,
    var pwd2Digit: String? = null // pwd_2digit는 카멜케이스로 변경
)