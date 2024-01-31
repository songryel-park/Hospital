package org.example.animalhospital.entity

import jakarta.persistence.Entity
import jakarta.persistence.*
import org.example.animalhospital.entity.dto.ReserveRequest
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.exception.BadRequestException
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "reservation")
class Reserve(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reserveId: Long? = null,

    @JoinColumn(name = "user_id", nullable = false)
    val userId: Long,

    @JoinColumn(name = "pet_id", nullable = false)
    val petId: Long,

    @Column(name = "disease", nullable = false)
    val disease : String,

    @Column(name = "reserve_date")
    var reserveDate: String,

    @Column(name = "reserve_create")
    val createAt: LocalDateTime,

    @Column(name = "reserve_update")
    var updateAt: LocalDateTime,

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    var status: ReserveStatus,

    @Column(name = "price")
    val price: Long = 50000
): Timestemped()