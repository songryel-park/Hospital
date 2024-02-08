package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.dto.ReserveRequest
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ReserveRecordProcessor: ItemProcessor<Reserve?, Reserve?> {
    override fun process(item: Reserve): Reserve {
        return item
    }
}