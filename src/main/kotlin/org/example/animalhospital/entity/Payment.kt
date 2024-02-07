package org.example.animalhospital.entity

import jakarta.persistence.*

@Entity
@Table(name = "payment")
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val paymentId: Long? = null,

    @JoinColumn(name = "reserve_id", nullable = false)
    val reserveId: Long,

    @JoinColumn(name = "wallet_id", nullable = false)
    val walletId: Long,
): Timestemped()