package org.example.animalhospital.exception

abstract class BaseException: RuntimeException() {
    open lateinit var code: ResultCode
    override lateinit var message: String
}