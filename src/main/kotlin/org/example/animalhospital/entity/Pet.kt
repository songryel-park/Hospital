package org.example.animalhospital.entity

import jakarta.persistence.*
import org.example.animalhospital.entity.enums.Species

@Entity
@Table(name = "pet")
data class Pet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val petId: Long? = null,

    @Column(name = "species", nullable = false)
    @Enumerated(EnumType.STRING)
    val species: Species,

    @Column(name = "name", nullable = false)
    val name : String,

    @Column(name = "birth", nullable = false)
    val birth : String,

    @Column(name = "adoption", nullable = false)
    val adoption : String,

    @Column(name = "weight", nullable = false)
    val weight : Long,

    @Column(name = "category")
    val category : String,

    @Column(name = "gender", nullable = false)
    val gender : Boolean,

    @Column(name = "neuter", nullable = false)
    val neuter : Boolean,

    @Column(name = "allergy")
    val allergy : String,
)