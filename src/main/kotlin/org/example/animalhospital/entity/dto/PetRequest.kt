package org.example.animalhospital.entity.dto

import org.example.animalhospital.entity.Pet
import org.example.animalhospital.entity.enums.Species

data class PetRequest(
    var userId: Long,
    var species: Species,
    var name: String,
    var birth: String,
    var adoption: String,
    var weight: Long,
    var variety: String?,
    var gender: Boolean,
    var neuter: Boolean,
    var allergy: String?
)