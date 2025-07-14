package com.loopers.domain.user;

public interface UserRepository {

  UserModel save(UserModel userModel);

  void duplicateUserId(String userId);
}
