package org.example.animalhospital.entity

import jakarta.persistence.*
import org.example.animalhospital.entity.enums.PayPalStatus

@Entity
@Table(name = "paypal_order")
class PayOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    @Column(name = "paypal_order_id")
    var paypalOrderId: String = ""

    @Column(name = "paypal_order_status")
    var orderStatus: PayPalStatus = PayPalStatus.ORDER
}