package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Component
class StatusWriter(private val repository: ReserveRepository): ItemWriter<Reserve> {
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    override fun write(chunk: Chunk<out Reserve>) {
        chunk.forEach { reservation -> reservation.status = ReserveStatus.RESERVED
            //repository.save(reservation)
        }
        repository.saveAll(chunk)
    }
}