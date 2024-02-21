package org.example.animalhospital.entity.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.example.animalhospital.entity.User
import org.example.animalhospital.entity.enums.Species

data class PetRequest(
    @JsonProperty("username")
    var username: String,
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