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
import java.time.LocalDateTime

data class ReserveRequest(
    var reserveId: Long?,

    @field:NotNull
    @JsonProperty("user_id")
    var userId: Long,

    @field:NotNull
    @JsonProperty("pet")
    var petId: Long,

    @field:NotNull
    @JsonProperty("disease")
    var disease: String,

    @field:NotNull
    @JsonProperty("reserve_date")
    var reserveDate: String,

    @field:NotNull
    var createAt: LocalDateTime,

    @field:NotNull
    var updateAt: LocalDateTime,

    @field:NotNull
    @JsonProperty("reservation")
    var status: ReserveStatus,

    @field:NotNull
    var price: Long
) {
    fun processPayment(price: Long, userId: Long): Boolean {
        return true
    }

    fun cancel() {
        if (status != ReserveStatus.TREATMENT_WAITING ||
            status != ReserveStatus.TREATMENT_BEING ||
            status != ReserveStatus.COMPLETED) {
            this.status = ReserveStatus.CANCEL
        } else {
            throw BadRequestException("결제를 취소할 수 없습니다.")
        }
    }

    companion object {
        fun fromEntity(reserve: Reserve): ReserveRequest {
            return ReserveRequest(
                reserveId = reserve.reserveId,
                userId = reserve.userId,
                petId = reserve.petId,
                disease = reserve.disease,
                reserveDate = reserve.reserveDate,
                createAt = reserve.createAt,
                updateAt = reserve.updateAt,
                status = reserve.status,
                price = reserve.price
            )
        }
    }
}