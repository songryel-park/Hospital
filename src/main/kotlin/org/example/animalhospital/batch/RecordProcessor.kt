package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class RecordProcessor: ItemProcessor<Reserve?, Reserve?> {
    override fun process(item: Reserve): Reserve {
        return item
    }
}