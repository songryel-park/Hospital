package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.repository.ReserveRepository
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Component
class StatusProcessor(private val repository: ReserveRepository): ItemProcessor<Reserve?, Reserve?> {
    //@Transactional(isolation = Isolation.REPEATABLE_READ)
    override fun process(item: Reserve): Reserve? {
        val locked = repository.findByIdWithLock(item.reserveId!!)
        if (locked!!.status == ReserveStatus.RESERVATION) {
            locked.status = ReserveStatus.RESERVED
        }
        return locked
    }
}