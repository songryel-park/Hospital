package org.example.animalhospital.repository

import jakarta.persistence.LockModeType
import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.enums.ReserveStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ReserveRepository: JpaRepository<Reserve, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Reserve r WHERE r.reserveId = :reserveId")
    fun findByIdWithLock(@Param("reserveId") reserveId: Long): Reserve?
    fun findByReserveDateBefore(reserveDate: LocalDateTime): List<Reserve>
    fun findByReserveDateBetween(start: LocalDateTime, end: LocalDateTime): List<Reserve>
    override fun findAll(pageable: Pageable): Page<Reserve>
    fun findReservationByUserId(pageable: Pageable, userId: Long): MutableList<Reserve>
}