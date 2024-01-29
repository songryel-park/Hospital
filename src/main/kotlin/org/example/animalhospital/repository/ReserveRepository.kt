package org.example.animalhospital.repository

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.dto.ReserveRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReserveRepository: JpaRepository<Long, Long> {
    fun findReserveById(keys: Set<Long>): MutableList<Reserve>
    fun getAll(pageable: Pageable): List<ReserveRequest>
    fun findReservationByUserId(pageable: Pageable, userId: Long): MutableList<ReserveRequest>
    fun payment(request: ReserveRequest, userId: Long): Boolean
}