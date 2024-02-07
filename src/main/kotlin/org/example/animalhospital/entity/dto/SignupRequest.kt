package org.example.animalhospital.entity.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import org.example.animalhospital.entity.User
import org.example.animalhospital.entity.enums.UserRole
import org.example.animalhospital.exception.BadRequestException
import org.springframework.security.crypto.password.PasswordEncoder

data class SignupRequest(
    var userId: Long?,

    @field:NotBlank
    @JsonProperty("username")
    var username: String,

    @field:NotBlank
    @JsonProperty("password")
    var password: String,
    private val passwordEncoder: PasswordEncoder,

    @field:NotBlank
    @JsonProperty("user_role")
    var role: String,
) {
    val user = User(
        username = username,
        password = passwordEncoder.encode(password),
        role = when(role) {
            "ADMIN" -> UserRole.ADMIN
            "CLIENT" -> UserRole.CLIENT
            else -> throw BadRequestException("잘못된 권한입니다.")
        }
    )
}