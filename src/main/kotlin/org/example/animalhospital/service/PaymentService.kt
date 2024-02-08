package org.example.animalhospital.service

import com.siot.IamportRestClient.IamportClient
import org.example.animalhospital.entity.Payment
import org.example.animalhospital.entity.dto.BillingRequest
import org.example.animalhospital.entity.dto.PaymentRequest
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.exception.NotFoundException
import org.example.animalhospital.payment.OrderClient
import org.example.animalhospital.repository.PaymentRepository
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val orderClient: OrderClient,
    private val reserveRepository: ReserveRepository,
) {
    private val iamportClient: IamportClient = IamportClient("{API_KEY}", "{API_SECRET}")

    @Transactional
    fun payment(request: PaymentRequest) {
        val reservation = reserveRepository.findById(request.reserveId)
            .orElseThrow { NotFoundException("해당 예약을 찾을 수 없습니다.") }
        if (reservation.status != ReserveStatus.PAYMENT_WAITING) {
            throw IllegalStateException("결제 대기 상태가 아닙니다.")
        }

        // IAMPORT 결제 요청 로직
//        paymentGateway.processPayment(request.paymentInfo)
        orderClient.processPayment(request)

        val payment = Payment(
            reserveId = request.reserveId,
            walletId = request.walletId,
        )
        paymentRepository.save(payment)
    }

    fun issueBilling(issueBilling: BillingRequest, impUid: String) {
        iamportClient.paymentByImpUid(impUid)

//        val billingCustomerData = BillingCustomerData(
//            null,
//            issueBilling.cardNumber,
//            issueBilling.expiry,
//            issueBilling.birth
//        )
//
//        1) 고객의 빌링키 발급 유무 확인
//        val paymentInfo = paymentDao.findByMemberId(issueBilling.memberId)
//
//
//        2) 기존에 빌링키를 발급한 적이 없었을 경우
//        if (paymentInfo == null) {
//            throw BaseException("빌링키 발급 필요.")
//
//            2-1) iamPort API 인가 토큰 발행
//            val auth: AccessToken = try {
//                iamportClient.
//            } catch (e: Exception) {
//                throw BaseException(BILLING_API_ERROR) // iamport 서버 인가토큰 발행 실패
//            }
//
            /**
             * =======================  FRONT SERVER  =======================
             */
//            2-2) 빌링키 발행
//            val billingKeyFoundation: BillingKeyFoundation
//            try {
//                billingCustomerData.pg = "kcp.T0000"
//                val billingCustomerInfo: IamportResponse<BillingCustomer> =
//                    iamportClient.postBillingCustomer(
//                        newCustomerUid,
//                        billingCustomerData
//                    )
//                println(billingCustomerInfo.response.toString())
//                billingKeyFoundation = BillingKeyFoundation(
//                    billingCustomerInfo.response.customerUid,  // 빌링키 매핑 id
//                    billingCustomerInfo.response.cardName,  // 카드 이름
//                    issueBilling.pw4
//                ) // 비밀번호 6자리
//            } catch (e: Exception) {
//                throw BaseException(BILLING_API_ERROR) // 빌링키 발행 실패
//            }
            /**
             * =======================  FRONT SERVER  =======================
             */
//            2-3) 발급받은 빌링키 저장
//            try {
//                val billIdx: Int = paymentDao.issueBilling(billingKeyFoundation)
//                return BillingRequest(billIdx)
//            } catch (e: Exception) {
//                throw BaseException(BILLING_API_ERROR) // 빌링키 앱 서버 DB저장 실패
//            }
        }

//         3) 기존에 발급받은 빌링키가 있을 경우
//            3-1) iamPort API 인가 토큰 발행
//            val auth: AccessToken = try {
//                iamportClient.
//            } catch (e: Exception) {
//                throw BaseException(BILLING_API_ERROR) // iamport 서버 인가토큰 발행 실패
//            }
//
//            3-2) 발행한 토큰과 기존 빌링키로 결제 요청
//
//        4) 랜덤 Customer_uid (빌링키에 매핑되는) 생성
//        var newCustomerUid: String
//        try {
//            newCustomerUid = createNewCustomerUid()
//            // DB에 해당 빌링키 존재하는지 중복 확인
//            // EXIST 1이면 존재, 0이면 존재 안함
//            while (paymentDao.checkExistBillingKey(newCustomerUid) == 1) {
//                newCustomerUid = createNewCustomerUid()
//            }
//            println("랜덤생성: $newCustomerUid")
//        } catch (e: Exception) {
//            throw BaseException(BILLING_API_ERROR) // 랜덤 customer_uid(빌링키 매핑) 생성 오류
//        }
}

//    override fun getService(): PaymentService {
//        return when {
//            paymentType == PaymentType.CREDIT_CARD -> PaymentService()
//            paymentType == PaymentType.BANK_TRANSFER -> PaymentService()
//            else -> throw BadRequestException("잘못된 카드/계좌 번호 입니다.")
//        }
//    }