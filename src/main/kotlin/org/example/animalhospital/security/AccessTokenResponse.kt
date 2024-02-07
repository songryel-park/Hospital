package org.example.animalhospital.security

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant


@JsonIgnoreProperties(ignoreUnknown = true)
class AccessTokenResponse {
    val scope: String? = null

    @JsonProperty("access_token")
    val accessToken: String? = null

    @JsonProperty("token_type")
    val tokenType: String? = null

    @JsonProperty("app_id")
    val applicationId: String? = null

    @JsonProperty("expires_in")
    val expiresIn: Long? = null

    private val nonce: String? = null

    @JsonIgnore
    val expiration: Instant? = null
}