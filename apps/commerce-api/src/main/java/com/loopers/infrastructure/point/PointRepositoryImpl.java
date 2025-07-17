package com.loopers.infrastructure.point;

import com.loopers.domain.point.PointModel;
import com.loopers.domain.point.PointRepository;
import com.loopers.domain.user.UserModel;
import com.loopers.infrastructure.user.UserJpaRepository;
import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

  @Override
  @Transactional
  public PointModel charge(String userId, int point) {

    List<UserModel> userExists = userRepository.findByUserId(userId);

    if (userExists.isEmpty()) {
      throw new CoreException(ErrorType.NOT_FOUND, "존재하지 않는 계정으로 충전할 수 없습니다.");
    }

    PointModel model = pointRepository.findByUserId(userId).orElse(new PointModel(userId,0));
    model.charge(point);
    pointRepository.save(model);

    return model;
  }
}
