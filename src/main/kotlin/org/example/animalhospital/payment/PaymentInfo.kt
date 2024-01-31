package org.example.animalhospital.payment

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import org.example.animalhospital.entity.enums.PaymentType

data class PaymentInfo(
    @field:NotNull
    @JsonProperty("user_id")
    val userId: Long,

    @field:NotNull
    val paymentType: PaymentType,

    @field:NotNull
    val name: String,

    @field:NotNull
    val company: String,

    @field:NotNull
    val number: String,

    val expiration: String,
)

//data class CardInfo(
//    @field:NotNull
//    @JsonProperty("user_id")
//    val userId: Long,
//
//    val category: String,
//
//    @field:NotNull
//    val name: String,
//
//    @field:NotNull
//    val company: String,
//
//    @field:NotNull
//    val number: String,
//
//    val expiration: String,
//
////    @field:NotBlank
////    val cvc: String
//): PaymentInfo()
//
//data class BankInfo(
//    @field:NotNull
//    @JsonProperty("user_id")
//    val userId: Long,
//
//    @field:NotNull
//    val company: String,
//
//    @field:NotNull
//    val name: String,
//
//    @field:NotNull
//    val account: String,
//): PaymentInfo()