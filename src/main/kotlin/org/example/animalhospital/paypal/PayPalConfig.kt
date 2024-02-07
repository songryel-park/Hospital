package org.example.animalhospital.paypal

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "paypal")
class PayPalConfig {
    @NotBlank
    val baseUrl: String = ""

    @NotBlank
    val clientId: String = ""

    @NotBlank
    val clientSecret: String = ""
}
