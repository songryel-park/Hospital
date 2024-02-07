package org.example.animalhospital.entity.enums

enum class PaypalEndpoint(private val path: String) {
    GET_ACCESS_TOKEN("/v1/oauth2/token"),
    GET_CLIENT_TOKEN("/v1/identity/generate-token"),
    ORDER_CHECKOUT("/v2/checkout/orders");

    companion object {
        fun createUrl(baseUrl: String, endpoint: PaypalEndpoint): String {
            return baseUrl + endpoint.path
        }

        fun createUrl(baseUrl: String, endpoint: PaypalEndpoint, vararg params: String?): String {
            return baseUrl + endpoint.path + String.format("/%s", *params)
        }
    }
}
