package com.loopers.infrastructure.point;

import com.loopers.domain.point.PointModel;
import com.loopers.domain.point.PointRepository;
import com.loopers.infrastructure.user.UserJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PointRepositoryImpl implements PointRepository {
  private final PointJpaRepository pointRepository;
  private final UserJpaRepository userRepository;


  @Override
  public PointModel get(String userId) {

    if (userRepository.findByUserId(userId).isEmpty()) {
      return null;
    }

    Optional<PointModel> point = pointRepository.findByUserId(userId);

    return point.get();
  }
}
