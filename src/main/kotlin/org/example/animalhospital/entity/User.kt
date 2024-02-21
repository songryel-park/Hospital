package org.example.animalhospital.entity

import jakarta.persistence.*
import lombok.NoArgsConstructor
import org.example.animalhospital.entity.enums.UserRole
import org.example.animalhospital.exception.InvalidInputException

@Entity
@NoArgsConstructor
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long? = null,

    @Column(name = "username", unique = true)
    val username: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    val role: UserRole
) {
    fun checkAdminOrThrow(){
        if(role != UserRole.ADMIN){
            throw InvalidInputException("권한이 없습니다.")
        }
    }

    fun checkClientOrThrow(){
        if(role != UserRole.CLIENT){
            throw InvalidInputException("권한이 없습니다.")
        }
    }
}