package com.loopers.interfaces.api.point;

import com.loopers.application.point.PointFacade;
import com.loopers.application.point.PointInfo;
import com.loopers.interfaces.api.ApiResponse;
import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

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

  @PostMapping("/charge")
  public ApiResponse<PointV1Dto.ChargeResponse> charge(@RequestHeader(name = "X-USER-ID") String userId,
                                                       @RequestBody PointV1Dto.ChargeRequest request) {

    if(userId.equals("not")) {
      throw new CoreException(ErrorType.NOT_FOUND, "존재하지 않는 계정입니다.");
    }

    return ApiResponse.success(
        new PointV1Dto.ChargeResponse(
            userId, 5000 + request.point()
        )
    );
  }


}
