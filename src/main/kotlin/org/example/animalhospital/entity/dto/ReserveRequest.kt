package org.example.animalhospital.entity.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.example.animalhospital.entity.Pet
import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.User
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.exception.BadRequestException
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

data class ReserveRequest(
    var reserveId: Long?,

    @field:NotNull
    @JsonProperty("user_id")
    var userId: Long,

    @field:NotNull
    @JsonProperty("pet")
    var petId: Long,

    @field:NotNull
    var createAt: CreatedDate,

    @field:NotNull
    var updateAt: LastModifiedDate,

    @field:NotNull
    @JsonProperty("reservation")
    var status: String,

    @field:NotNull
    @JsonProperty("disease")
    var disease: String,

    @field:NotBlank
    val price: Long
) {
    val reservation = Reserve(
        userId = userId,
        petId = petId,
        createAt = createAt,
        updateAt = updateAt,
        status = when(status) {
            "Reservation" -> ReserveStatus.RESERVATION
            "Payment" -> ReserveStatus.PAYMENT_WAITING
            "Reserved" -> ReserveStatus.RESERVED
            "Waiting" -> ReserveStatus.TREATMENT_WAITING
            "Treatment" -> ReserveStatus.TREATMENT_BEING
            "Completed" -> ReserveStatus.COMPLETED
            "Cancel" -> ReserveStatus.CANCEL
            else -> throw BadRequestException("예약된 내용이 없습니다.")
        },
        disease = disease
    )

    fun toCreate(): Reserve = Reserve(
        reserveId, userId, petId, createAt, updateAt, status = ReserveStatus.RESERVATION, disease, price)

    fun processPayment(price: Long, userId: Long): Boolean {
        return true
    }
}