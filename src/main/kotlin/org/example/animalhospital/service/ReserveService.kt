package org.example.animalhospital.service

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.User
import org.example.animalhospital.entity.dto.ReserveRequest
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.exception.BadRequestException
import org.example.animalhospital.exception.NotFoundException
import org.example.animalhospital.repository.ReserveRepository
import org.example.animalhospital.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReserveService(
    private val reserveRepository: ReserveRepository,
    private val userRepository: UserRepository,
) {
    fun findAll(pageable: Pageable): Page<Reserve> {
        val allReservation = reserveRepository.findAll(pageable)
        return allReservation
    }

    fun find(pageable: Pageable, userId: Long): MutableList<Reserve> {
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
            username = request.username,
            petname = request.petname,
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

        if (reservation.status != ReserveStatus.RESERVED) {
            throw BadRequestException("예약을 변경할 수 없습니다.")
        }
        reservation.reserveDate = request.reserveDate
        return reserveRepository.save(reservation)
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