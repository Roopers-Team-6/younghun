package com.loopers.interfaces.api.point;

import com.loopers.application.point.PointFacade;
import com.loopers.application.point.PointInfo;
import com.loopers.interfaces.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/points")
@RequiredArgsConstructor
public class PointV1Controller implements PointV1ApiSpec {
  private final PointFacade pointFacade;

  @Override
  @GetMapping
  public ApiResponse<PointV1Dto.PointResponse> get(@RequestHeader(name = "X-USER-ID") String userId) {
    PointInfo pointInfo = pointFacade.get(userId);
    return ApiResponse.success(PointV1Dto.PointResponse.toDto(pointInfo));
  }
}
