package org.example.animalhospital.entity.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.enums.ReserveStatus
import java.time.LocalDateTime

data class ReserveRequest(
    var reserveId: Long?,
    @JsonProperty("user")
    var username: String,
    @JsonProperty("pet")
    var petname: String,
    var disease: String,
    var reserveDate: LocalDateTime,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var status: ReserveStatus,
    var price: Long
) {
    companion object {
        fun fromEntity(reserve: Reserve): ReserveRequest {
            return ReserveRequest(
                reserveId = reserve.reserveId,
                username = reserve.username,
                petname = reserve.petname,
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