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

    @Column(name = "user_name", unique = true)
    val username: String,

    @Column(name = "password", unique = true)
    var password: String,

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    val role: UserRole,

    @Column(name = "account")
    val account: String
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