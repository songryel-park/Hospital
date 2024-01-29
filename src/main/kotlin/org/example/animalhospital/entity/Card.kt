package org.example.animalhospital.entity

import jakarta.persistence.*

@Entity
@Table(name = "card")
class Card(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val cardId: Long? = null,

    @JoinColumn(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "company", nullable = false)
    val company: String,

    @Column(name = "expiration_date", nullable = false)
    val expiration: String
)