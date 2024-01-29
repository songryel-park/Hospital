package org.example.animalhospital.repository

import org.example.animalhospital.entity.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PetRepository: JpaRepository<Long, Long>{
    fun findPetByUserId(userId: Long): MutableList<Pet>
}