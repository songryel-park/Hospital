package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ReserveRecordReader(private val repository: ReserveRepository): ItemReader<Reserve> {
    private var currentIndex = 0

    override fun read(): Reserve? {
        val reserveRecords: List<Reserve> = repository.findByReserveDateBefore(LocalDateTime.now().minusYears(5L))
        if (reserveRecords.isEmpty()) {
            println("진료내역이 없습니다.")
            return null
        }
        return reserveRecords.getOrNull(currentIndex++)
    }
}