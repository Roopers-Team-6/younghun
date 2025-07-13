package com.loopers.application.user;

import com.loopers.application.example.ExampleInfo;
import com.loopers.domain.example.ExampleModel;
import com.loopers.domain.example.ExampleService;
import com.loopers.domain.user.UserModel;
import com.loopers.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {
  private final UserService userService;

  public UserInfo createUser(UserInfo userInfo) {
    UserModel user = userService.createUser(userInfo.toModel());
    return UserInfo.from(user);
  }
}
