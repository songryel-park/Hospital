package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class StatusReader(private val repository: ReserveRepository): ItemReader<Reserve> {
    private var currentIndex = 0

    override fun read(): Reserve? {
        val start = LocalDateTime.now()
        val end = LocalDateTime.now().plusDays(1L)

        val reserveList: List<Reserve> = repository.findByReserveDateBetween(start, end)
        if (reserveList.isEmpty()) {
            println("예약내역이 없습니다.")
            return null
        }
        return reserveList.getOrNull(currentIndex++)
    }
}