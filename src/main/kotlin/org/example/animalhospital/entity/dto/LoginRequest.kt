package org.example.animalhospital.entity.dto

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank
    var username: String,

    @field:NotBlank
    var password: String
)