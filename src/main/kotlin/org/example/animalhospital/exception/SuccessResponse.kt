package org.example.animalhospital.exception


class SuccessResponse(message: String): BaseException() {
    override var code: ResultCode = ResultCode.SUCCESS
    override var message: String = message
}