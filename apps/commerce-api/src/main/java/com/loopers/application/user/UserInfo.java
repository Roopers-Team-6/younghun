package com.loopers.application.user;

import com.loopers.domain.user.UserModel;
import java.time.LocalDate;

public record UserInfo(String userId, String email, LocalDate birthday) {

  public static UserInfo from(UserModel model) {
    return new UserInfo(
        model.getUserId(),
        model.getEmail(),
        model.getBirthday()
    );
  }

  public UserModel toModel() {
    return UserModel.builder()
        .userId(userId)
        .email(email)
        .birthday(birthday)
        .build();
  }
}
