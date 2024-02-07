package org.example.animalhospital.entity.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.example.animalhospital.entity.enums.PayPalIntent
import org.example.animalhospital.paypal.PurchaseUnit
import java.io.Serializable

data class PayPalDto(
    var intent: PayPalIntent,
    @JsonProperty("purchase_units")
    var purchaseUnits: List<PurchaseUnit>,
    @JsonProperty("application_context")
    var applicationContext: PayAppContext
): Serializable