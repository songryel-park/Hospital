package org.example.animalhospital.service

import org.example.animalhospital.entity.dto.PaymentRequest
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.exception.BadRequestException
import org.example.animalhospital.exception.NotFoundException
import org.example.animalhospital.payment.PaymentGateway
import org.example.animalhospital.repository.PaymentRepository
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentGateway: PaymentGateway,
    private val reserveRepository: ReserveRepository
) {
    fun payment(request: PaymentRequest) {
        val reservation = reserveRepository.findById(request.reserveId)
        if (reservation.status == ReserveStatus.COMPLETED) {
            throw IllegalStateException("진료를 완료했습니다.")
        }
        reservation.status = ReserveStatus.PAYMENT_WAITING
        reserveRepository.save(reservation)

        try {
            paymentGateway.processPayment(request.paymentInfo)
        } catch (e: BadRequestException) {
            reservation.status = ReserveStatus.PAYMENT_WAITING
            reserveRepository.save(reservation)
            throw e
        }

        val payment = PaymentRequest(
            reserveId = request.reserveId,
            paymentInfo = request.paymentInfo,
        )
        paymentRepository.save(payment)
    }

//    override fun getService(): PaymentService {
//        return when {
//            paymentType == PaymentType.CREDIT_CARD -> PaymentService()
//            paymentType == PaymentType.BANK_TRANSFER -> PaymentService()
//            else -> throw BadRequestException("잘못된 카드/계좌 번호 입니다.")
//        }
//    }
}

