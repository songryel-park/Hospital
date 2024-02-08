package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.dto.ReserveRequest
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class ReserveRecordWriter (private val repository: ReserveRepository): ItemWriter<Reserve?> {
//    private var currentIndex = 0

    override fun write(chunk: Chunk<out Reserve?>) {
//        if (chunk.items.isNotEmpty()) {
//            repository.delete(chunk.items[currentIndex])
//        }
        for (reserve in chunk.items) {
            if (reserve != null) {
                repository.delete(reserve)
            }
        }
    }
}