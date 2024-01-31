package org.example.animalhospital.repository

import org.example.animalhospital.entity.Wallet
import org.springframework.data.jpa.repository.JpaRepository

interface WalletRepository: JpaRepository<Wallet, Long>