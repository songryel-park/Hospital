package org.example.animalhospital.exception

import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class Response<T>(
    val resultCode: String = ResultCode.SUCCESS.name,
    val data: T? = null,
    val message: String = ResultCode.SUCCESS.message
)