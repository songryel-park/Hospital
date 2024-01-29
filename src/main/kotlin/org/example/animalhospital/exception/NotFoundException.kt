package org.example.animalhospital.exception

class NotFoundException(message: String): BaseException() {
    override var code: ResultCode = ResultCode.NOT_FOUND
    override var message: String = message
}