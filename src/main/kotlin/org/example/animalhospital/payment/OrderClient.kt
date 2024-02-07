package org.example.animalhospital.payment

import com.siot.IamportRestClient.response.IamportResponse
import org.example.animalhospital.entity.dto.PaymentRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Component
class OrderClient(
    private val restTemplate: RestTemplate,
    @Value("\${portone.api.url}") private val apiUrl: String,
    @Value("\${portone.api.key}") private val apiKey: String,
) {
    fun processPayment(request: PaymentRequest): ResponseEntity<*> {
        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer $apiKey")
        val requestEntity = HttpEntity(request, headers)
        return restTemplate.exchange("$apiUrl/payment", HttpMethod.POST, requestEntity, String::class.java)
    }
}