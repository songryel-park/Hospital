package org.example.animalhospital.paypal

import com.siot.IamportRestClient.request.BillingCustomerData
import com.siot.IamportRestClient.response.AccessToken
import com.siot.IamportRestClient.response.BillingCustomer
import com.siot.IamportRestClient.response.IamportResponse
import org.example.animalhospital.entity.Payment
import org.example.animalhospital.entity.dto.LoginRequest
import org.example.animalhospital.payment.PaymentInfo
import retrofit2.Call
import retrofit2.http.*

interface PayPalApi {
    @POST("/user/getToken")
    fun token(@Body auth: LoginRequest): Call<IamportResponse<AccessToken>>

    @POST("/subscribe/customer/{customer_uid}")
    fun postBillingCustomer(
        @Header("Authorization") token: String,
        @Path("customer_uid") customerUid: String,
        @Body billingData: BillingCustomerData
    ): Call<IamportResponse<BillingCustomer>>

    fun creditPayment(paymentInfo: PaymentInfo) {
//        val amount = Amount()
//            .setCurrency("USD")
//            .setTotal(paymentInfo.amount.toString())
//
//        val instrument = FundingInstrument()
//            .setCreditCard(paymentInfo.creditCard)
//
//        val transaction = Transaction()
//            .setDescription(paymentInfo.description)
//            .setAmount(amount)
//
//        val payer = Payer()
//            .setPaymentMethod("credit_card")
//            .setFundingInstruments(listOf(instrument))
//
//        val payment = Payment()
//            .setIntent("sale")
//            .setPayer(payer)
//            .setTransactions(listOf(transaction))
//
//        try {
//            val createdPayment = payment.create(appContext)
//            // PayPal에서 반환한 결제 결과를 PaymentResult로 변환하여 반환
//            return convertToPaymentResult(createdPayment)
//        } catch (e: PayPalRESTException) {
//            throw ServerException("PayPal API 호출 중 오류 발생")
//        }
//    }
//
//    // PayPal의 반환값을 PaymentResult로 변환하는 메서드
//    private fun convertToPaymentResult(createdPayment: Payment): PaymentResult {
//        val state = createdPayment.state
//        val message = createdPayment.transactions?.firstOrNull()?.description
//        val successful = state.equals("approved", ignoreCase = true)
//
//        return PaymentResult(successful, message)
    }

    fun accountPayment(paymentInfo: PaymentInfo)
}
