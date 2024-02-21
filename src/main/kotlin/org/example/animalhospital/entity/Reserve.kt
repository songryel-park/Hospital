package org.example.animalhospital.entity

import jakarta.persistence.Entity
import jakarta.persistence.*
import org.example.animalhospital.entity.dto.ReserveRequest
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.exception.BadRequestException
import java.time.LocalDateTime

@Entity
@Table(name = "reservation")
class Reserve(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reserveId: Long? = null,

    @JoinColumn(name = "user_id")
    val userId: Long,

    @JoinColumn(name = "pet_id")
    val petId: Long,

    @Column(name = "disease")
    val disease : String,

    @Column(name = "reserve_date")
    var reserveDate: LocalDateTime,

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    var status: ReserveStatus,

    @Column(name = "total_price")
    val price: Long
): Timestemped() {
    fun cancel() {
        if (this.status != ReserveStatus.RESERVED) {
            throw BadRequestException("결제를 취소할 수 없습니다.")
        }

        if (LocalDateTime.now().isAfter(reserveDate)) {
            this.status = ReserveStatus.CANCEL
        } else {
            throw BadRequestException("아직 예약 시간이 지나지 않았습니다.")
        }
    }
}