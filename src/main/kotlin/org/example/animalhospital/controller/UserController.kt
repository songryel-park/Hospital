package org.example.animalhospital.controller

import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.example.animalhospital.entity.dto.LoginRequest
import org.example.animalhospital.entity.dto.SignupRequest
import org.example.animalhospital.exception.Response
import org.example.animalhospital.security.TokenInfo
import org.example.animalhospital.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {
    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody request: SignupRequest): ResponseEntity<String> {
        userService.signup(request)
        return ResponseEntity("회원가입 완료", HttpStatus.OK)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): Response<TokenInfo> {
        val tokenInfo = userService.login(request)
        return Response(data = tokenInfo)
    }
}