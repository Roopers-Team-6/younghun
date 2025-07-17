package com.loopers.interfaces.api.point;

import com.loopers.application.point.PointInfo;

public class PointV1Dto {

  public record PointResponse(String userId, int point) {

    public static PointResponse toDto(PointInfo pointInfo) {
      return new PointResponse(
          pointInfo.userId(),
          pointInfo.point()
      );
    }
  }
}
