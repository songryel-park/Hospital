package org.example.animalhospital.controller

import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.example.animalhospital.entity.User
import org.example.animalhospital.entity.dto.ReserveRequest
import org.example.animalhospital.entity.enums.ReserveStatus
import org.example.animalhospital.exception.BadRequestException
import org.example.animalhospital.service.PaymentService
import org.example.animalhospital.service.ReserveService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
class ReserveController(private val reserveService: ReserveService) {
    @GetMapping("/reservations")
    fun getAllReservation(@RequestParam(defaultValue = "0") page: Int,
                          @RequestParam(defaultValue = "10") size: Int): ResponseEntity<List<ReserveRequest>> {
        val getAll = reserveService.getAll(PageRequest.of(page, size))
        return ResponseEntity.ok(getAll)
    }

    @GetMapping("/reservations/{userId}")
    fun findReservation(@RequestParam(defaultValue = "0") page: Int,
                        @RequestParam(defaultValue = "10") size: Int,
                        @PathVariable userId: Long): ResponseEntity<List<ReserveRequest>> {
        val find = reserveService.find(PageRequest.of(page, size), userId)
        return ResponseEntity.ok(find)
    }

    @GetMapping("/reservations/{reservationId}")
    fun findReservationDetail(@PathVariable reserveId: Long): ResponseEntity<ReserveRequest> {
        val findDetail = reserveService.findDetail(reserveId)
        return ResponseEntity.ok(findDetail)
    }

    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @PostMapping("/reservations")
    fun createReservation(@Valid @RequestBody request: ReserveRequest): ResponseEntity<String> {
        reserveService.create(request)
        return ResponseEntity("예약되었습니다.", HttpStatus.OK)
    }

    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @PutMapping("/reservations/{reservationId}")
    fun updateReservation(@AuthenticationPrincipal user: User, @PathVariable reserveId: Long,
                        @Valid @RequestBody request: ReserveRequest): ResponseEntity<String> {
        if (reserveId != request.reserveId!!) {
            throw BadRequestException("수정하려는 예약이 아닙니다.")
        }
        reserveService.update(request)
        return ResponseEntity("예약내용이 수정되었습니다", HttpStatus.OK)
    }

    @DeleteMapping("/reservations/{reservationId}")
    fun cancelReservation(@AuthenticationPrincipal user: User,
                          @Valid @PathVariable reserveId: Long): ResponseEntity<Unit> {
        reserveService.cancel(reserveId)
        return ResponseEntity.noContent().build()
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/reservations/{reservationId}")
    fun editReservation(@AuthenticationPrincipal user: User,
                          @Valid @PathVariable request: ReserveRequest): ResponseEntity<ReserveRequest> {
        reserveService.edit(request)
        return ResponseEntity.ok(request)
    }
}