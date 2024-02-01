package org.example.animalhospital.entity.enums

enum class ReserveStatus(val value: String) {
    RESERVATION("예약등록"),
    PAYMENT_WAITING("결제대기"),
    RESERVED("예약확정"),
    TREATMENT_WAITING("진료대기"),
    TREATMENT_BEING("진료중"),
    COMPLETED("진료완료"),
    CANCEL("예약취소")
}