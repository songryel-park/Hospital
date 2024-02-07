package org.example.animalhospital.entity.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.example.animalhospital.entity.enums.OrderPage

data class PayAppContext(
    @JsonProperty("brand_name")
    var brandName: String? = null,

    @JsonProperty("landing_page")
    var landingPage: OrderPage? = null,

    @JsonProperty("return_url")
    var returnUrl: String? = null,

    @JsonProperty("cancel_url")
    var cancelUrl: String? = null,
)