package org.example.animalhospital.service

import org.example.animalhospital.entity.enums.PaymentType
import org.example.animalhospital.exception.*
import org.example.animalhospital.payment.*
import org.springframework.stereotype.Service

@Service
class PayPalPaymentGateway(val paypalApi: PayPalApi) : PaymentGateway {
    override fun processPayment(paymentInfo: PaymentInfo) {
        try {
            when (paymentInfo.paymentType) {
                PaymentType.CREDIT_CARD -> paypalApi.creditPayment(paymentInfo)
                PaymentType.BANK_TRANSFER -> paypalApi.accountPayment(paymentInfo)
            }
        } catch(e: BadRequestException) {
            throw BadRequestException("결제정보가 일치하지 않습니다.")
        } catch(e: ServerException) {
            throw ServerException("서버에러가 발생했습니다.")
        }
    }
//
//    private fun creditPayment(paymentInfo: CardInfo?) {
//        val cardInfo = paymentInfo
//        try {
//            val paymentResult = paypalApi.creditPayment(cardInfo)
//        } catch() {
//            throw
//        } catch() {
//            throw
//        }
//    }
//
//    private fun accountPayment(paymentInfo: BankInfo?) {
//        val bankInfo = paymentInfo
//        try {
//            val paymentResult = paypalApi.accountPayment(bankInfo)
//        } catch () {
//            throw ("rurfwp")
//        } catch(ServerEx) {
//            throw waktlgn
//        }
//    }
}
