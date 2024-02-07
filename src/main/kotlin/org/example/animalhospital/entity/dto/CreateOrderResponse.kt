package org.example.animalhospital.entity.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.example.animalhospital.entity.enums.PayPalStatus

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateOrderResponse(
    val paypalOrderId: String,
    val status: PayPalStatus,
    val links: List<LinkDto>,
)