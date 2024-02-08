package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.dto.ReserveRequest
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ReserveRecordReader(private val repository: ReserveRepository): ItemReader<Reserve> {
    private var currentIndex = 0
    private var reserveRecords: MutableList<Reserve> = mutableListOf()

    override fun read(): Reserve {
        if (reserveRecords.isEmpty()) {
            val reserves: List<Reserve> = repository.findByReserveDateBefore(LocalDateTime.now().minusYears(5L))
            reserveRecords.addAll(reserves)
        }

        val nextRecord = reserveRecords.getOrNull(currentIndex)
        if (nextRecord == null) {
            throw Exception("5년전 진료내역이 없습니다 !")
        }
        currentIndex++

        return nextRecord
    }
}