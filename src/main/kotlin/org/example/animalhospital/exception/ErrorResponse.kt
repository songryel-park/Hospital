package org.example.animalhospital.exception

class ErrorResponse(message: String): BaseException() {
    override var code: ResultCode = ResultCode.ERROR
    override var message: String = message
}