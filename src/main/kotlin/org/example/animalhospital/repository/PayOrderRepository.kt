package org.example.animalhospital.repository

import org.example.animalhospital.entity.PayOrder
import org.springframework.data.jpa.repository.JpaRepository

interface PayOrderRepository: JpaRepository<PayOrder, Long> {
    fun findByPaypalOrderId(paypalOrderId: String?)
}