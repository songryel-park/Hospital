package org.example.animalhospital.entity

import jakarta.persistence.Entity
import jakarta.persistence.*
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.exception.BadRequestException
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

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

    @Column(name = "reserve_create")
    val createAt: CreatedDate,

    @Column(name = "reserve_update")
    var updateAt: LastModifiedDate,

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    var status: ReserveStatus,

    @Column(name = "disease", nullable = false)
    val disease : String,

    @Column(name = "price")
    val price: Long = 50000
): Timestemped() {
    fun cancel() {
        if (status != ReserveStatus.TREATMENT_WAITING ||
            status != ReserveStatus.TREATMENT_BEING ||
            status != ReserveStatus.COMPLETED) {
            this.status = ReserveStatus.CANCEL
        } else {
            throw BadRequestException("결제를 취소할 수 없습니다.")
        }
    }
}