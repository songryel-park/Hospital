package org.example.animalhospital.controller

import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.example.animalhospital.entity.Reserve
import org.example.animalhospital.entity.User
import org.example.animalhospital.entity.dto.ReserveRequest
import org.example.animalhospital.exception.BadRequestException
import org.example.animalhospital.service.ReserveService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
class ReserveController(private val reserveService: ReserveService) {
    @GetMapping("/reservation")
    fun getAllReservation(@RequestParam(defaultValue = "0") page: Int,
                          @RequestParam(defaultValue = "10") size: Int): ResponseEntity<Page<Reserve>> {
        val getAll = reserveService.findAll(PageRequest.of(page, size))
        return ResponseEntity.ok(getAll)
    }

    @GetMapping("/reservation/{userId}")
    fun findReservation(@RequestParam(defaultValue = "0") page: Int,
                        @RequestParam(defaultValue = "10") size: Int,
                        @PathVariable userId: Long): ResponseEntity<List<Reserve>> {
        val find = reserveService.find(PageRequest.of(page, size), userId)
        return ResponseEntity.ok(find)
    }

    @GetMapping("/reservation/{reserveId}")
    fun findReservationDetail(@PathVariable reserveId: Long): ResponseEntity<ReserveRequest> {
        val findDetail = reserveService.findDetail(reserveId)
        return ResponseEntity.ok(findDetail)
    }

    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @PostMapping("/reservation")
    fun createReservation(@AuthenticationPrincipal user: User, @PathVariable userId: Long,
                          @Valid @RequestBody request: ReserveRequest): ResponseEntity<String> {
        reserveService.create(userId, request)
        return ResponseEntity("예약되었습니다.", HttpStatus.OK)
    }

    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @PutMapping("/reservation/{reservationId}")
    fun updateReservation(@AuthenticationPrincipal userId: Long, @PathVariable reserveId: Long,
                        @Valid @RequestBody request: ReserveRequest): ResponseEntity<String> {
        if (reserveId != request.reserveId!!) {
            throw BadRequestException("수정하려는 예약이 아닙니다.")
        }
        reserveService.update(userId, request)
        return ResponseEntity("예약내용이 수정되었습니다", HttpStatus.OK)
    }

    @DeleteMapping("/reservation/{reservationId}")
    fun cancelReservation(
        @AuthenticationPrincipal user: User,
        @PathVariable reserveId: Long
    ): ResponseEntity<Unit> {
        reserveService.cancel(reserveId)
        return ResponseEntity.noContent().build()
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/reservation/{reservationId}")
    fun treatment(@AuthenticationPrincipal user: User, @PathVariable userId: Long,
                        @Valid @PathVariable request: ReserveRequest): ResponseEntity<ReserveRequest> {
        reserveService.complete(userId, request)
        return ResponseEntity.ok(request)
    }
}