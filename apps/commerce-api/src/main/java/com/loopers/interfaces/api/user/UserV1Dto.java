package com.loopers.interfaces.api.user;

import com.loopers.application.user.UserInfo;
import java.time.LocalDate;

public class UserV1Dto {

  public record UserRequest(String userId, String email, LocalDate birthday) {

    public UserInfo to() {
      return new UserInfo(
          userId,
          email,
          birthday
      );
    }
  }

  public record UserResponse(String userId, String email, LocalDate birthday) {
    public static UserResponse from(UserInfo info) {
      return new UserResponse(
          info.userId(),
          info.email(),
          info.birthday()
      );
    }
  }
}
