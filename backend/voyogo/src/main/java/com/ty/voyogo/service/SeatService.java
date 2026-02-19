package com.ty.voyogo.service;

import com.ty.voyogo.repository.SeatLockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public interface SeatService {

    void lockSeat(Long tripId, Long seatId, String username);
    void unlockSeat(Long tripId, Long seatId, String username);
}
