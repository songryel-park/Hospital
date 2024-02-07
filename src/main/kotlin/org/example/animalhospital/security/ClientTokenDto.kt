package org.example.animalhospital.security

import com.fasterxml.jackson.annotation.JsonProperty

data class ClientTokenDto(
    @JsonProperty("client_token")
    val clientToken: String,

    @JsonProperty("expires_in")
    val expiresIn: Long
)