package org.example.animalhospital.exception

class ServerException(message: String): BaseException() {
    override var code: ResultCode = ResultCode.INTERNAL_SERVER_ERROR
    override var message: String = message
}