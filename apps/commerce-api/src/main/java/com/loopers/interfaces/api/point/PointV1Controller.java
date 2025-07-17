package com.loopers.interfaces.api.point;

import com.loopers.interfaces.api.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/points")
public class PointV1Controller {


  @GetMapping
  public ApiResponse<PointV1Dto.PointResponse> get(@RequestHeader(name = "X-USER-ID") String userId) {
    return ApiResponse.success(new PointV1Dto.PointResponse(
        userId,
        5000
    ));
  }
}
