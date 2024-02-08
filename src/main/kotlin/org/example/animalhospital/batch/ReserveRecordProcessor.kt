package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.dto.ReserveRequest
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ReserveRecordProcessor: ItemProcessor<Reserve, Reserve> {
    override fun process(item: Reserve): Reserve {
        return item
//        val currentDate = LocalDateTime.now()
//        val recordDate = item.reserveDate
//        val targetDate = currentDate.minusYears(5L)
//
//        return if (recordDate.isBefore(targetDate)) {
//            // 5년 이상된 기록은 삭제
//            item.reserveId?.let { reserveId ->
//                repository.deleteById(reserveId)
//            }
//            null // 삭제내역은 반환하지 않음
//        } else {
//            item
//        }
    }
}