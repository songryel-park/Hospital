package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class StatusReader(private val repository: ReserveRepository): ItemReader<Reserve> {
    private var currentIndex = 0

    override fun read(): Reserve? {
        val reserveList: List<Reserve> = repository.findByReserveDateBefore(LocalDateTime.now().minusDays(1L))
        if (reserveList.isEmpty()) {
            println("진료내역이 없습니다.")
            return null
        }

        if (currentIndex < reserveList.size) {
            val nextReserve = reserveList[currentIndex]
            currentIndex++
            return nextReserve
        }
        return null
    }
}