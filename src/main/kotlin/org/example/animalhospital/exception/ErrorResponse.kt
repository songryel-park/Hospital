package org.example.animalhospital.exception

data class ErrorResponse(
    var code: ResultCode? = null,
    var message: String? = null
)