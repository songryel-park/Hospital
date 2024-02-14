package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
class RecordWriter (private val repository: ReserveRepository): ItemWriter<Reserve?> {
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