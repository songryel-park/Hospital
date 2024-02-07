package org.example.animalhospital.paypal

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.animalhospital.entity.dto.CreateOrderResponse
import org.example.animalhospital.entity.dto.PayPalDto
import org.example.animalhospital.entity.enums.PaypalEndpoint
import org.example.animalhospital.security.AccessTokenResponse
import org.example.animalhospital.security.ClientTokenDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets
import java.util.*

@Component
class PayPalHttpClient @Autowired constructor(
    private val paypalConfig: PayPalConfig,
    private val objectMapper: ObjectMapper
) {

    private val httpClient: HttpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build()

    companion object {
        private const val BEARER_TYPE = "Bearer "
    }

    @Throws(Exception::class)
    fun getAccessToken(): AccessTokenResponse {
        val request = HttpRequest.newBuilder()
            .uri(URI.create(createUrl(paypalConfig.baseUrl, PaypalEndpoint.GET_ACCESS_TOKEN.toString())))
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, encodeBasicCredentials())
            .header(HttpHeaders.ACCEPT_LANGUAGE, "en_US")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
            .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        val content = response.body()

        return objectMapper.readValue(content, AccessTokenResponse::class.java)
    }

    @Throws(Exception::class)
    fun getClientToken(): ClientTokenDto {
        val accessTokenDto = getAccessToken()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(createUrl(paypalConfig.baseUrl, PaypalEndpoint.GET_CLIENT_TOKEN.toString())))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, BEARER_TYPE + accessTokenDto.accessToken)
            .header(HttpHeaders.ACCEPT_LANGUAGE, "en_US")
            .POST(HttpRequest.BodyPublishers.noBody())
            .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        val content = response.body()

        return objectMapper.readValue(content, ClientTokenDto::class.java)
    }

    @Throws(Exception::class)
    fun createOrder(order: PayPalDto): CreateOrderResponse {
        val accessTokenDto = getAccessToken()
        val payload = objectMapper.writeValueAsString(order)

        val request = HttpRequest.newBuilder()
            .uri(URI.create(createUrl(paypalConfig.baseUrl, PaypalEndpoint.ORDER_CHECKOUT.toString())))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, BEARER_TYPE + accessTokenDto.accessToken)
            .POST(HttpRequest.BodyPublishers.ofString(payload))
            .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        val content = response.body()

        return objectMapper.readValue(content, CreateOrderResponse::class.java)
    }

    private fun encodeBasicCredentials(): String {
        val input = "${paypalConfig.clientId}:${paypalConfig.clientSecret}"
        return "Basic " + Base64.getEncoder().encodeToString(input.toByteArray(StandardCharsets.UTF_8))
    }

    private fun createUrl(vararg paths: String): String {
        return paths.joinToString("/")
    }
}