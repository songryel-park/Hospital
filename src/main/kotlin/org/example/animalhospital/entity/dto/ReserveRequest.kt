package org.example.animalhospital.entity.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.enums.ReserveStatus
import java.time.LocalDateTime

data class ReserveRequest(
    var reserveId: Long?,

    @field:NotNull
    @JsonProperty("user_id")
    var userId: Long,

    @field:NotNull
    @JsonProperty("pet")
    var petId: Long,

    @field:NotBlank
    @JsonProperty("disease")
    var disease: String,

    @field:NotBlank
    @JsonProperty("reserve_date")
    var reserveDate: String,

    @field:NotNull
    var createdAt: LocalDateTime,

    @field:NotNull
    var updatedAt: LocalDateTime,

    @field:NotNull
    @JsonProperty("reservation")
    var status: ReserveStatus,

    var price: Long
) {
    companion object {
        fun fromEntity(reserve: Reserve): ReserveRequest {
            return ReserveRequest(
                reserveId = reserve.reserveId,
                userId = reserve.userId,
                petId = reserve.petId,
                disease = reserve.disease,
                reserveDate = reserve.reserveDate,
                createdAt = reserve.createdAt,
                updatedAt = reserve.updatedAt,
                status = reserve.status,
                price = reserve.price
            )
        }
    }
}