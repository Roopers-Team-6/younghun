package com.loopers.domain.point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PointModelTest {

  @DisplayName("포인트를 충전할때,")
  @Nested
  class Charge {

    @DisplayName("0 이하의 정수로 포인트를 충전 시 실패한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void returnBadRequest_whenChargingZeroOrLessPoints(int point) {
      // arrange
      String userId = "test";
      PointModel model = new PointModel(userId, point);
      // act
      CoreException result = assertThrows(CoreException.class, () -> model.charge(point));
      // assert
      assertThat(result.getErrorType()).isEqualTo(ErrorType.BAD_REQUEST);
    }
  }
}
