package org.example.animalhospital.service

import org.example.animalhospital.entity.Payment
import org.example.animalhospital.entity.dto.PaymentRequest
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.exception.NotFoundException
import org.example.animalhospital.payment.PaymentGateway
import org.example.animalhospital.repository.PaymentRepository
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentGateway: PaymentGateway,
    private val reserveRepository: ReserveRepository
) {
    @Transactional
    fun payment(request: PaymentRequest) {
        val reservation = reserveRepository.findById(request.reserveId)
            .orElseThrow { NotFoundException("해당 예약을 찾을 수 없습니다.") }
        if (reservation.status != ReserveStatus.PAYMENT_WAITING) {
            throw IllegalStateException("결제 대기 상태가 아닙니다.")
        }

        paymentGateway.processPayment(request.paymentInfo)

        val payment = Payment(
            reserveId = request.reserveId,
            walletId = request.walletId,
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

