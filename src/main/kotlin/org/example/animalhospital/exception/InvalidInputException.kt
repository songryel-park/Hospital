package org.example.animalhospital.exception

class InvalidInputException(message: String): BaseException() {
    override var code: ResultCode = ResultCode.FORBIDDEN
    override var message: String = message
}