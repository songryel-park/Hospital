package org.example.animalhospital.exception

class JwtException(message: String): BaseException() {
    override var code: ResultCode = ResultCode.UNAUTHORIZED
    override var message: String = message
}