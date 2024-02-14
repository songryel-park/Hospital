package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.exception.NotFoundException
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class StatusProcessor(private val repository: ReserveRepository): ItemProcessor<Reserve?, Reserve?> {
    override fun process(item: Reserve): Reserve? {
        val reservation = repository.findById(item.reserveId!!)
            .orElseThrow { NotFoundException("해당 예약을 찾을 수 없습니다.") }

        if (reservation.status == ReserveStatus.RESERVATION && reservation.reserveDate == LocalDateTime.now().minusDays(1L)) {
            reservation.status = ReserveStatus.RESERVED
            return repository.save(reservation)
        }
        return null
    }
}