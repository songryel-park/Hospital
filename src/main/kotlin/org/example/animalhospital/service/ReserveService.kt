package org.example.animalhospital.service

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.User
import org.example.animalhospital.entity.dto.ReserveRequest
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.exception.NotFoundException
import org.example.animalhospital.repository.ReserveRepository
import org.example.animalhospital.repository.UserRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReserveService(
    private val reserveRepository: ReserveRepository,
    private val userRepository: UserRepository
) {
    fun getAll(pageable: Pageable): List<ReserveRequest> {
        val allReservation = reserveRepository.getAll(pageable)
        return allReservation
    }

    fun find(pageable: Pageable, userId: Long): MutableList<ReserveRequest> {
        val myReservation = reserveRepository.findReservationByUserId(pageable, userId)
        return myReservation
    }

    fun findDetail(reserveId: Long): ReserveRequest {
        val reservation = reserveRepository.findById(reserveId)
            .orElseThrow { NotFoundException("존재하지 않는 예약입니다.") }
        return ReserveRequest.fromEntity(reservation)
    }

    @Transactional
    fun create(userId: Long, request: ReserveRequest): Reserve {
        val user: User = userRepository.findById(userId)
            .orElseThrow { NotFoundException("존재하지 않는 계정입니다.") }
        user.checkClientOrThrow()

        val reservation = Reserve(
            userId = request.userId,
            petId = request.petId,
            disease = request.disease,
            reserveDate = request.reserveDate,
            status = ReserveStatus.RESERVATION,
            price = request.price
        )
        return reserveRepository.save(reservation)
    }

    @Transactional
    fun update(userId: Long, request: ReserveRequest): Reserve {
        val user: User = userRepository.findById(userId)
            .orElseThrow { NotFoundException("존재하지 않는 계정입니다.") }
        user.checkClientOrThrow()

        val reservation = reserveRepository.findById(request.reserveId!!)
            .orElseThrow { NotFoundException("잘못된 예약번호 입니다.") }
        reservation.reserveDate = request.reserveDate
        return reservation
    }

    @Transactional
    fun cancel(reserveId: Long) {
        val reservation = reserveRepository.findById(reserveId)
            .orElseThrow { throw NotFoundException("잘못된 예약번호 입니다.") }
        reservation.cancel()
    }

    @Transactional
    fun complete(userId: Long, request: ReserveRequest): Reserve {
        val user: User = userRepository.findById(userId)
            .orElseThrow { NotFoundException("존재하지 않는 계정입니다.") }
        user.checkAdminOrThrow()

        val reservation = reserveRepository.findById(request.reserveId!!)
            .orElseThrow { NotFoundException("잘못된 예약번호 입니다.") }
        reservation.status = ReserveStatus.PAYMENT_WAITING
        return reservation
    }
}