package org.example.animalhospital.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.example.animalhospital.entity.enums.PaymentType
import org.example.animalhospital.payment.PaymentInfo

@Entity
@Table(name = "wallet")
class Wallet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val walletId: Long? = null,

    @JoinColumn(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "name")
    val name: String,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "payment_type")
    val paymentType: PaymentType,

    @Column(name = "company")
    val company: String,

    @Column(name = "number")
    val number: String,

    @Column(name = "expiration")
    val expiration: String,
)