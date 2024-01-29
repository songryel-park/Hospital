package org.example.animalhospital.service


import org.example.animalhospital.entity.User
import org.example.animalhospital.entity.dto.LoginRequest
import org.example.animalhospital.entity.dto.SignupRequest
import org.example.animalhospital.exception.DuplicateException
import org.example.animalhospital.repository.UserRepository
import org.example.animalhospital.security.JwtTokenProvider
import org.example.animalhospital.security.TokenInfo
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
) {
    fun signup(request: SignupRequest): User {
        val result = userRepository.existsByUsername(request.username)
        if (result) {
            throw DuplicateException("중복된 ID입니다")
        }
        val user = request.user
        return userRepository.save(user)
    }

    fun login(request: LoginRequest): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(request.username, request.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }
}