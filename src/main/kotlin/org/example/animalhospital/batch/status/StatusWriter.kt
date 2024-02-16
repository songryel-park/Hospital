package org.example.animalhospital.batch.status

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
class StatusWriter(private val repository: ReserveRepository): ItemWriter<Reserve> {
    override fun write(chunk: Chunk<out Reserve>) {
        repository.saveAll(chunk)
    }
}