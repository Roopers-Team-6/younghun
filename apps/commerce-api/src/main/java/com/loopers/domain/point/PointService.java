package com.loopers.domain.point;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

  private final PointRepository pointRepository;

  public PointModel get(String userId) {
    return pointRepository.get(userId);
  }
}
