package org.example.animalhospital.entity.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.payment.PaymentInfo
import org.example.animalhospital.entity.enums.PaymentType
import org.example.animalhospital.entity.enums.ReserveStatus
import java.time.LocalDateTime

data class PaymentRequest(
    var reserveId: Long,
    var paymentInfo: PaymentInfo,
)