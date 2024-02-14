package org.example.animalhospital.repository

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.enums.ReserveStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ReserveRepository: JpaRepository<Reserve, Long> {
    fun findByReserveDateBefore(reserveDate: LocalDateTime): List<Reserve>
    fun findByReserveDateBeforeAndStatus(reserveDate: LocalDateTime, status: ReserveStatus): List<Reserve>
    override fun findAll(pageable: Pageable): Page<Reserve>
    fun findReservationByUserId(pageable: Pageable, userId: Long): MutableList<Reserve>
}