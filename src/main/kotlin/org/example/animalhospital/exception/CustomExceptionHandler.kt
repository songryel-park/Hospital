package org.example.animalhospital.exception

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomExceptionHandler {
    @ExceptionHandler(BaseException::class)
    protected fun baseException(e: BaseException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(e.code.status)
            .body(ErrorResponse(e.code, e.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun methodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage ?: "Not Exception Message"
        }

        return ResponseEntity(ErrorResponse(ResultCode.BAD_REQUEST, errors.toString()), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(SignatureException::class)
    fun signatureException(ex: SignatureException): ResponseEntity<ErrorResponse> {
        val errors = mapOf("유효토큰이 아님" to (ex.message ?: "토큰이 유효하지 않습니다."))
        return ResponseEntity(ErrorResponse(ResultCode.WRONG_TYPE_TOKEN, errors.toString()), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(MalformedJwtException::class)
    fun malformedJwtException(ex: MalformedJwtException): ResponseEntity<ErrorResponse> {
        val errors = mapOf("잘못된 토큰" to (ex.message ?: "토큰이 올바르지 않습니다."))
        return ResponseEntity(ErrorResponse(ResultCode.UNSUPPORTED_TOKEN, errors.toString()), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(ExpiredJwtException::class)
    fun expiredJwtException(ex: ExpiredJwtException): ResponseEntity<ErrorResponse> {
        val errors = mapOf("만료된 토큰" to (ex.message ?: "토큰이 만료되었습니다."))
        return ResponseEntity(ErrorResponse(ResultCode.EXPIRED_TOKEN, errors.toString()), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(Exception::class)
    protected fun defaultException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errors = mapOf("미처리 에러" to (ex.message ?: "Not Exception Message"))
        return ResponseEntity(ErrorResponse(ResultCode.ERROR, errors.toString()), HttpStatus.BAD_REQUEST)
    }
}