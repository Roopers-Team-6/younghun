package com.loopers.infrastructure.user;

import com.loopers.domain.user.UserModel;
import com.loopers.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
  private final UserJpaRepository repository;

  @Override
  public UserModel save(UserModel userModel) {
    return repository.save(userModel);
  }
}
