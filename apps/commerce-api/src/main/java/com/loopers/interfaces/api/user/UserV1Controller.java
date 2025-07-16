package com.loopers.interfaces.api.user;

import com.loopers.application.user.UserFacade;
import com.loopers.application.user.UserInfo;
import com.loopers.interfaces.api.ApiResponse;
import com.loopers.interfaces.api.user.UserV1Dto.UserGetResponse;
import com.loopers.interfaces.api.user.UserV1Dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserV1Controller implements UserV1ApiSpec {
  private final UserFacade userFacade;

  @Override
  @PostMapping
  public ApiResponse<UserV1Dto.UserResponse> createUser(@RequestBody @Validated UserV1Dto.UserRequest request) {
    UserInfo user = userFacade.createUser(request.to());
    return ApiResponse.success(UserResponse.from(user));
  }

  @Override
  @GetMapping("/{userId}")
  public ApiResponse<UserV1Dto.UserGetResponse> getUser(@PathVariable String userId) {
    UserInfo user = userFacade.getUser(userId);
    return ApiResponse.success(UserGetResponse.from(user));
  }
}
